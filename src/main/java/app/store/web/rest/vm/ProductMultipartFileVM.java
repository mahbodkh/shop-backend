package app.store.web.rest.vm;

import app.store.service.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

public class ProductMultipartFileVM extends ProductDto {

    private List<MultipartFile> file = new ArrayList<>();

    public List<MultipartFile> getFile() {
        return file;
    }

    public void setFile(List<MultipartFile> file) {
        this.file = file;
    }

    @Override
    public String toString() {
        return "ProductMultipartFileVM{" +
                "file=" + file +
                '}';
    }
}
