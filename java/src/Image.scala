package src

import jni.{GLWrapper, Viewport}
import src.util.Types.Texture

class Image(textureName: String, isPNG: Boolean) {
    // Texture to draw with
    val texture: Texture = GLWrapper.generateTexture(textureName, isPNG)

    def draw(x: Int, y: Int, width: Int, height: Int) {
        GLWrapper.useTexture(texture, 0)
        Viewport.drawImage(x, y, width, height)
    }
}