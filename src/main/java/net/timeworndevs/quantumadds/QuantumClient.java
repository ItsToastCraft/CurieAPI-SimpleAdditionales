package net.timeworndevs.quantumadds;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;
import net.timeworndevs.quantumadds.block.ModBlocks;
import net.timeworndevs.quantumadds.entities.ModEntities;
import net.timeworndevs.quantumadds.entities.client.GooberModel;
import net.timeworndevs.quantumadds.entities.client.ModModelLayers;
import net.timeworndevs.quantumadds.entities.client.MonstrocityRenderer;
import net.timeworndevs.quantumadds.render.ArmorTestRenderer;
import net.timeworndevs.quantumadds.render.HazmatSuitModel;

@Environment(EnvType.CLIENT)
public class QuantumClient implements ClientModInitializer {
    public static final EntityModelLayer HAZMAT_SUIT_FEET_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID, "hazmat_suit"), "feet");
    public static final EntityModelLayer HAZMAT_SUIT_MAIN_LAYER = new EntityModelLayer(new Identifier(Quantum.MOD_ID,"hazmat_suit"), "main");
    public static boolean isFiguraLoaded;
    @Override
    public void onInitializeClient() {

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MONSTROCITY, GooberModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MONSTROCITY, MonstrocityRenderer::new);

        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.REACTOR_CORE, RenderLayer.getTranslucent());
        // Here we will put client-only registration code (thabks toast)

        ArmorTestRenderer.register();
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_SUIT_FEET_LAYER, () -> TexturedModelData.of(HazmatSuitModel.getModelData(), 64, 64));
        EntityModelLayerRegistry.registerModelLayer(HAZMAT_SUIT_MAIN_LAYER, () -> TexturedModelData.of(HazmatSuitModel.getModelData(), 64, 64));
        // This entrypoint is suitable for setting up client-specific logic, such as rendering.
        isFiguraLoaded = (FabricLoader.getInstance().isModLoaded("figura"));
    }
}