package application.data.service.page;

import application.data.model.Color;
import application.data.repository.web.iColorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImp implements iColorService {
    @Autowired
    private iColorRepository iColorRepository;
    @Override
    public List<Color> findAllColor() {
        return (List<Color>) iColorRepository.findAll();
    }

    @Override
    public Color findOneColor(int colorId) {
        return iColorRepository.findOne(colorId);
    }
}
