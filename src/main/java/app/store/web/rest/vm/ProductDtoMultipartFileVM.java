package app.store.web.rest.vm;

import app.store.service.dto.ProductDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

public class ProductDtoMultipartFileVM {

    private ProductDto productDto;
    private MultipartFile[] files;

    public ProductDto getProductDto() {
        return productDto;
    }

    public void setProductDto(ProductDto productDto) {
        this.productDto = productDto;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "ProductDtoMultipartFileVM{" +
                "productDto=" + productDto +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}
