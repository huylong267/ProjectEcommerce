package application.data.service.page;

import application.data.model.Size;

import java.util.List;

public interface iSizeService {
    List<Size> findAllSize();
    Size findOneSize(int sizeId);
}
