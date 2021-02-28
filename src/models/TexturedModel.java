package models;

import textures.ModelTexture;

public class TexturedModel {

    RawModelData model;
    ModelTexture texture;

    public TexturedModel(RawModelData model, ModelTexture texture) {

        this.model = model;
        this.texture = texture;

    }

    public RawModelData getModel() {
        return model;
    }

    public ModelTexture getTextures() {
        return texture;
    }

}
