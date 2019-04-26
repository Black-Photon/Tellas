package src

import jni.{GLWrapper, Viewport}

class Image(textureName: String, isPNG: Boolean) {
    type Texture = Int

    // Texture to draw with
    val texture: Texture = GLWrapper.generateTexture(textureName, isPNG)

    def draw(x: Int, y: Int) {
        GLWrapper.useTexture(texture, 0)
        Viewport.drawImage(x, y, 10, 10)
    }
}