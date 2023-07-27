/*
 * LiquidBounce Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge.
 * https://github.com/CCBlueX/LiquidBounce/
 */
package net.ccbluex.liquidbounce.features.module

import net.ccbluex.liquidbounce.features.module.modules.client.JelloTabGui
import Recall.Clickgui.*
import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.KeyEvent
import net.ccbluex.liquidbounce.event.Listenable
import net.ccbluex.liquidbounce.features.module.modules.hyt.*
import net.ccbluex.liquidbounce.features.module.modules.combat.*
import net.ccbluex.liquidbounce.features.module.modules.exploit.*
import net.ccbluex.liquidbounce.features.module.modules.misc.*
import net.ccbluex.liquidbounce.features.module.modules.movement.*
import net.ccbluex.liquidbounce.features.module.modules.player.*
import net.ccbluex.liquidbounce.features.module.modules.render.*
import net.ccbluex.liquidbounce.features.module.modules.world.*
import net.ccbluex.liquidbounce.features.module.modules.client.*
import net.ccbluex.liquidbounce.features.module.modules.world.Timer
import net.ccbluex.liquidbounce.utils.ClientUtils
import Recall.Modules.*
import net.ccbluex.liquidbounce.utils.MinecraftInstance
import java.util.*


class ModuleManager : Listenable {

    val modules = TreeSet<Module> { module1, module2 -> module1.name.compareTo(module2.name) }
    private val moduleClassMap = hashMapOf<Class<*>, Module>()
    public   var toggleVolume = 0F

    init {
        LiquidBounce.eventManager.registerListener(this)
    }

    /**
     * Register all modules
     */
    fun registerModules() {
        ClientUtils.getLogger().info("[ModuleManager] Loading modules...")
        registerModules(
                GlowESP::class.java,
                NoC03::class.java,
                KillAura2::class.java,
                FakeBan::class.java,
                CustomColor::class.java,
                HYTHackerChecker::class.java,
                HYTDisabler::class.java,
                LBPlusScaffold::class.java,
                ESP2D::class.java,
                Compass::class.java,
                JelloTabGui::class.java,
                JelloArraylist::class.java,
                Title::class.java,
                FPSInfo::class.java,
                PingInfo::class.java,
                Logo::class.java,
                JelloGui::class.java,
                LBPlusScaffold::class.java,
                BWSpeedMine::class.java,
                HytBhop::class.java,
                NewGui::class.java,
                DMGParticle::class.java,
                SessionStats::class.java,
                ScaffoldFix::class.java,
                BlockFlyHelper::class.java,
                GodLightSync::class.java,
                ScaffoldHelper::class.java,
                ColorMixer::class.java,
                Kills::class.java,
                FollowTargetHud::class.java,
                PlayerSize::class.java,
                AutoPlay::class.java,
                HitAutoBlock::class.java,
                AttackMiss::class.java,
                kafix::class.java,
                BanChecker::class.java,
                BetterFPS::class.java,
                AutoArmor::class.java,
                AutoBow::class.java,
                AutoLeave::class.java,
                StateSound::class.java,
                AutoPot::class.java,
                AutoSoup::class.java,
                AutoWeapon::class.java,
                BowAimbot::class.java,
                Criticals::class.java,
                KillAura::class.java,
                Trigger::class.java,
                Fly::class.java,
                ClickGUI::class.java,
                AutoLobby::class.java,
                NoFucker::class.java,
                HighJump::class.java,
                CustomTitle::class.java,
                InventoryMove::class.java,
                LiquidWalk::class.java,
                SafeWalk::class.java,
                WallClimb::class.java,
                Strafe::class.java,
                Sprint::class.java,
                Teams::class.java,
                NoRotateSet::class.java,
                AntiBot::class.java,
                ChestStealer::class.java,
                Scaffold::class.java,
                CivBreak::class.java,
                Tower::class.java,
                FastBreak::class.java,
                FastPlace::class.java,
                ESP::class.java,
                HytGetName::class.java,
                HytBypass::class.java,
                NoSlow::class.java,
                Velocity::class.java,
                Ambience::class.java,
                Speed::class.java,
                Tracers::class.java,
                NameTags::class.java,
                FastUse::class.java,
                Teleport::class.java,
                Fullbright::class.java,
                ItemESP::class.java,
                StorageESP::class.java,
                Projectiles::class.java,
                NoClip::class.java,
                Nuker::class.java,
                PingSpoof::class.java,
                FastClimb::class.java,
                Step::class.java,
                AutoRespawn::class.java,
                AutoTool::class.java,
                NoWeb::class.java,
                Spammer::class.java,
                IceSpeed::class.java,
                Zoot::class.java,
                Regen::class.java,
                NoFall::class.java,
                Blink::class.java,
                NameProtect::class.java,
                NoHurtCam::class.java,
                Ghost::class.java,
                MidClick::class.java,
                XRay::class.java,
                Timer::class.java,
                Sneak::class.java,
                SkinDerp::class.java,
                Paralyze::class.java,
                GhostHand::class.java,
                AutoWalk::class.java,
                AutoBreak::class.java,
                FreeCam::class.java,
                Aimbot::class.java,
                Eagle::class.java,
                HitBox::class.java,
                AntiCactus::class.java,
                Plugins::class.java,
                AntiHunger::class.java,
                ConsoleSpammer::class.java,
                LongJump::class.java,
                Parkour::class.java,
                LadderJump::class.java,
                FastBow::class.java,
                MultiActions::class.java,
                AirJump::class.java,
                AutoClicker::class.java,
                NoBob::class.java,
                BlockOverlay::class.java,
                NoFriends::class.java,
                BlockESP::class.java,
                Chams::class.java,
                Clip::class.java,
                Phase::class.java,
                ServerCrasher::class.java,
                NoFOV::class.java,
                FastStairs::class.java,
                SwingAnimation::class.java,
                Derp::class.java,
                ReverseStep::class.java,
                TNTBlock::class.java,
                InventoryCleaner::class.java,
                TrueSight::class.java,
                LiquidChat::class.java,
                AntiBlind::class.java,
                NoSwing::class.java,
                BedGodMode::class.java,
                BugUp::class.java,
                Breadcrumbs::class.java,
                AbortBreaking::class.java,
                PotionSaver::class.java,
                CameraClip::class.java,
                WaterSpeed::class.java,
                Ignite::class.java,
                SlimeJump::class.java,
                MoreCarry::class.java,
                NoPitchLimit::class.java,
                Cape::class.java,
                Kick::class.java,
                Liquids::class.java,
                AtAllProvider::class.java,
                AirLadder::class.java,
                GodMode::class.java,
                TeleportHit::class.java,
                ForceUnicodeChat::class.java,
                ItemTeleport::class.java,
                BufferSpeed::class.java,
                SuperKnockback::class.java,
                ProphuntESP::class.java,
                AutoFish::class.java,
                Damage::class.java,
                Freeze::class.java,
                KeepContainer::class.java,
                VehicleOneHit::class.java,
                Reach::class.java,
                Rotations::class.java,
                NoJumpDelay::class.java,
                BlockWalk::class.java,
                AntiAFK::class.java,
                PerfectHorseJump::class.java,
                HUD::class.java,
                TNTESP::class.java,
                ComponentOnHover::class.java,
                PotionRender::class.java,
                KeepAlive::class.java,
                ResourcePackSpoof::class.java,
                NoSlowBreak::class.java,
                PortalMenu::class.java,
                EnchantEffect::class.java,
                SpeedMine::class.java,
                Animations::class.java,
                AutoHead::class.java

        )

        registerModule(NoScoreboard)
        registerModule(Fucker)
        registerModule(ChestAura)

        ClientUtils.getLogger().info("[ModuleManager] Loaded ${modules.size} modules.")
    }

    /**
     * Register [module]
     */
    fun registerModule(module: Module) {
        if (!module.isSupported)
            return

        modules += module
        moduleClassMap[module.javaClass] = module

        generateCommand(module)
        LiquidBounce.eventManager.registerListener(module)
    }
    fun getModuleInCategory(Category: ModuleCategory): ArrayList<Module> {
        val moduleInCategory = ArrayList<Module>()
        for (i in this.modules) {
            if (i.category != Category)
                continue
            moduleInCategory.add(i)
        }
        return moduleInCategory
    }

    /**
     * Register [moduleClass]
     */
    private fun registerModule(moduleClass: Class<out Module>) {
        try {
            registerModule(moduleClass.newInstance())
        } catch (e: Throwable) {
            ClientUtils.getLogger().error("Failed to load module: ${moduleClass.name} (${e.javaClass.name}: ${e.message})")
        }
    }

    /**
     * Register a list of modules
     */
    @SafeVarargs
    fun registerModules(vararg modules: Class<out Module>) {
        modules.forEach(this::registerModule)
    }

    private fun registerModule(cbModule: Any?) {
        registerModule((cbModule as Class<out Module>).newInstance())
    }

    /**
     * Unregister module
     */
    fun unregisterModule(module: Module) {
        modules.remove(module)
        moduleClassMap.remove(module::class.java)
        LiquidBounce.eventManager.unregisterListener(module)
    }

    /**
     * Generate command for [module]
     */
    internal fun generateCommand(module: Module) {
        val values = module.values

        if (values.isEmpty())
            return

        LiquidBounce.commandManager.registerCommand(ModuleCommand(module, values))
    }

    /**
     * Legacy stuff
     *
     * TODO: Remove later when everything is translated to Kotlin
     */

    /**
     * Get module by [moduleClass]
     */
    fun getModule(moduleClass: Class<*>) = moduleClassMap[moduleClass]!!

    operator fun get(clazz: Class<*>) = getModule(clazz)

    /**
     * Get module by [moduleName]
     */
    fun getModule(moduleName: String?) = modules.find { it.name.equals(moduleName, ignoreCase = true) }

    /**
     * Module related events
     */

    /**
     * Handle incoming key presses
     */
    @EventTarget
    private fun onKey(event: KeyEvent) = modules.filter { it.keyBind == event.key }.forEach { it.toggle() }

    override fun handleEvents() = true
}
