package net.ccbluex.liquidbounce.features.module.modules.render;

import net.ccbluex.liquidbounce.features.module.Module;
import net.ccbluex.liquidbounce.features.module.ModuleCategory;
import net.ccbluex.liquidbounce.features.module.ModuleInfo;
import Recall.Clickgui.Jello.Jello;

/**
 * Skid by Recall.
 *
 * @Date 2023/04/02
 */
@ModuleInfo(name="JelloClickGui", description = "Jello", category = ModuleCategory.RENDER, canEnable = false)
public class JelloGui extends Module {
    @Override
    public void onEnable() {
        mc.displayGuiScreen(classProvider.wrapGuiScreen(new Jello()));
    }
}
