package app.store.service.io;//package app.store.util.io;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import org.openstack4j.api.OSClient.OSClientV3;
//import org.openstack4j.api.client.IOSClientBuilder.V3;
//import org.openstack4j.core.transport.Config;
//import org.openstack4j.model.common.ActionResponse;
//import org.openstack4j.model.common.Identifier;
//import org.openstack4j.model.common.Payloads;
//import org.openstack4j.model.identity.v3.Endpoint;
//import org.openstack4j.model.identity.v3.Service;
//import org.openstack4j.model.identity.v3.Token;
//import org.openstack4j.model.identity.v3.builder.EndpointBuilder;
//import org.openstack4j.model.storage.object.SwiftObject;
//import org.openstack4j.model.storage.object.options.CreateUpdateContainerOptions;
//import org.openstack4j.model.storage.object.options.ObjectListOptions;
//import org.openstack4j.model.storage.object.options.ObjectPutOptions;
//import org.openstack4j.openstack.OSFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//public class FileManager {
//    private static Token token = null;
//    private static Config config = Config.newConfig().withSSLVerificationDisabled();
//    private static Boolean swiftEnable = null;
//    private static String publicSwiftUrl = null;
//
//    public FileManager() {
//    }
//
//    private static Token getSwiftToken(boolean shouldRefreshToken) {
//        if (token == null || shouldRefreshToken) {
//            getToken(shouldRefreshToken);
//        }
//
//        return token;
//    }
//
//    private static synchronized void getToken(boolean refresh) {
//        try {
//            if (token == null || refresh) {
//                Identifier domainIdentifier = Identifier.byId(ConfigReader.getSwift().getString("domain", "Default"));
//                OSClientV3 os = (OSClientV3)((V3)((V3)OSFactory.builderV3().withConfig(config)).endpoint(ConfigReader.getSwift().getString("auth_url"))).credentials(ConfigReader.getSwift().getString("username"), ConfigReader.getSwift().getString("password"), domainIdentifier).authenticate();
//                token = os.getToken();
//                String public_url = ConfigReader.getSwift().getString("public_url");
//                if (!General.isEmpty(public_url)) {
//                    List<? extends Service> catalogList = token.getCatalog();
//                    if (catalogList != null) {
//                        Iterator var5 = catalogList.iterator();
//
//                        while(true) {
//                            while(true) {
//                                List endpoints;
//                                do {
//                                    Service catalog;
//                                    do {
//                                        if (!var5.hasNext()) {
//                                            return;
//                                        }
//
//                                        catalog = (Service)var5.next();
//                                    } while(!catalog.getName().equalsIgnoreCase("swift"));
//
//                                    endpoints = catalog.getEndpoints();
//                                } while(endpoints == null);
//
//                                int i = 0;
//
//                                for(Iterator var9 = endpoints.iterator(); var9.hasNext(); ++i) {
//                                    Endpoint endpoint = (Endpoint)var9.next();
//                                    if (endpoint.getIface().value().equalsIgnoreCase("PUBLIC")) {
//                                        EndpointBuilder endpointBuilder = (EndpointBuilder)endpoint.toBuilder();
//                                        endpointBuilder.url(new URL(public_url));
//                                        endpointBuilder.name(endpoint.getName());
//                                        endpointBuilder.type(endpoint.getType());
//                                        endpointBuilder.iface(endpoint.getIface());
//                                        endpointBuilder.region(endpoint.getRegion());
//                                        endpointBuilder.id(endpoint.getId());
//                                        endpointBuilder.regionId(endpoint.getRegionId());
//                                        endpoints.set(i, endpointBuilder.build());
//                                        break;
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        } catch (Exception var12) {
//            var12.printStackTrace();
//        }
//
//    }
//
//    public static List<? extends SwiftObject> listDirectory(String appid, String path, String marker, int limit) {
//        OSClientV3 osClientV3 = OSFactory.clientFromToken(getSwiftToken(false), config);
//        osClientV3.objectStorage().containers().create(appid, CreateUpdateContainerOptions.create().accessRead(".r:*"));
//        ObjectListOptions listOptions = ObjectListOptions.create().path(path);
//        if (marker != null && !marker.isEmpty()) {
//            listOptions.marker(marker);
//        }
//
//        if (limit > 0) {
//            listOptions.limit(limit);
//        }
//
//        return osClientV3.objectStorage().objects().list(appid, listOptions);
//    }
//
//    public static String createDirectory(String appid, String newpath) {
//        OSClientV3 osClientV3 = OSFactory.clientFromToken(getSwiftToken(false), config);
//        osClientV3.objectStorage().containers().create(appid, CreateUpdateContainerOptions.create().accessRead(".r:*"));
//        System.out.println("FileManager - createDirectory (" + newpath + ") in appid(" + appid + ")");
//        return osClientV3.objectStorage().containers().createPath(appid, General.addSlash(newpath));
//    }
//
//    public static ActionResponse deleteDirectory(String appid, String path) {
//        OSClientV3 osClientV3 = OSFactory.clientFromToken(getSwiftToken(false), config);
//        System.out.println("FileManager - deleteDirectory (" + path + ") in appid(" + appid + ")");
//        return osClientV3.objectStorage().objects().delete(appid, path);
//    }
//
//    public static String uploadFile(String appid, String path, File file, String originalFileName) {
//        path = General.removeTrailingSlash(path);
//        if (General.isEmpty(originalFileName)) {
//            originalFileName = file.getName();
//        }
//
//        OSClientV3 osClientV3 = OSFactory.clientFromToken(getSwiftToken(false), config);
//        osClientV3.objectStorage().containers().create(appid, CreateUpdateContainerOptions.create().accessRead(".r:*"));
//        Map<String, String> md = osClientV3.objectStorage().objects().getMetadata(appid, originalFileName);
//        ObjectPutOptions objectPutOptions = ObjectPutOptions.create().metadata(md);
//        if (!General.isEmpty(path)) {
//            objectPutOptions.path(path);
//        }
//
//        System.out.println("FileManager - uploadFile (" + file.getAbsolutePath() + ") to path (" + path + ") with name (" + originalFileName + ") in appid(" + appid + ")");
//        return osClientV3.objectStorage().objects().put(appid, originalFileName, Payloads.create(file), objectPutOptions);
//    }
//
//    public static String getPathBase(String appid) {
//        String path_base = ConfigReaderDatabase.getSettingsInMongo(appid).getString("path_base");
//        if (path_base == null) {
//            path_base = ConfigReader.getConfig().getString("path_base");
//        }
//
//        return path_base;
//    }
//
//    protected static boolean saveFile(MultipartFile file, String savetofile_path) {
//        boolean filesaved = General.saveMultipartFileToDisk(file, savetofile_path);
//        return filesaved;
//    }
//
//    private static boolean isSwiftEnable() {
//        if (swiftEnable == null) {
//            getSwiftEnable(false);
//        }
//
//        return swiftEnable;
//    }
//
//    private static synchronized void getSwiftEnable(boolean refresh) {
//        if (swiftEnable == null || refresh) {
//            swiftEnable = ConfigReader.getSwift().getBoolean("enable");
//            publicSwiftUrl = ConfigReader.getSwift().getString("public_url");
//        }
//
//    }
//
//    private static String getImagedomain(String appid) {
//        String imagedomain = ConfigReaderDatabase.getSettingsInMongo(appid).getString("imagedomain");
//        if (imagedomain == null) {
//            imagedomain = ConfigReader.getConfig().getString("imagedomain");
//        }
//
//        return imagedomain;
//    }
//
//    private static String getFiledomain(String appid) {
//        String imagedomain = ConfigReaderDatabase.getSettingsInMongo(appid).getString("filedomain");
//        if (imagedomain == null) {
//            imagedomain = ConfigReader.getConfig().getString("filedomain");
//        }
//
//        return imagedomain;
//    }
//
//    private static boolean isFileUrlSwift(String url) {
//        return url.startsWith("/swift/");
//    }
//
//    private static boolean isFilePathSwift(String path) {
//        return path.equals("swift");
//    }
//
//    public static String getImageUrl(String appid, String path, String url) {
//        String result = url;
//        if (!url.startsWith("http")) {
//            if ((isFilePathSwift(path) || isFileUrlSwift(url)) && isSwiftEnable()) {
//                result = publicSwiftUrl + url.replace("/swift", "");
//            } else {
//                String imagedomain = getImagedomain(appid);
//                result = imagedomain + url;
//            }
//        }
//
//        return result;
//    }
//
//    public static String getFileUrl(String appid, String path, String url) {
//        String result = url;
//        if (!url.startsWith("http")) {
//            if ((isFilePathSwift(path) || isFileUrlSwift(url)) && isSwiftEnable()) {
//                result = publicSwiftUrl + url.replace("/swift", "");
//            } else {
//                String filedomain = getFiledomain(appid);
//                result = filedomain + url;
//            }
//        }
//
//        return result;
//    }
//}