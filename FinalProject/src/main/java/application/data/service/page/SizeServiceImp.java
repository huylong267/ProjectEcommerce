package application.data.service.page;

import application.data.model.Size;
import application.data.repository.web.iSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImp implements iSizeService {
    @Autowired
    private iSizeRepository iSizeRepository;
    @Override
    public List<Size> findAllSize() {
        return (List<Size>) iSizeRepository.findAll();
    }

    @Override
    public Size findOneSize(int sizeId) {
        return iSizeRepository.findOne(sizeId);
    }
}
