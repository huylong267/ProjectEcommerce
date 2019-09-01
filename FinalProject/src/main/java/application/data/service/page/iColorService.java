package application.data.service.page;

import application.data.model.Color;

import java.util.List;

public interface iColorService {
    List<Color> findAllColor();
    Color findOneColor(int colorId);
}
