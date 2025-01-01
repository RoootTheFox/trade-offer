package gay.rooot.tradeoffer.client.mixins;

import gay.rooot.tradeoffer.TradeOfferMeow;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.MerchantScreenHandler;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.village.TradeOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gay.rooot.tradeoffer.TradeOfferMeow.TRADE_OFFER;

@Mixin(net.minecraft.client.gui.screen.ingame.MerchantScreen.class)
public abstract class VillagerUIMixin extends HandledScreen<MerchantScreenHandler> {
    @Unique
    boolean isTradingRightMeow = false;

    public VillagerUIMixin(MerchantScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(at = @At("HEAD"), method = "render")
    public void render(DrawContext context, int mouseX, int mouseY, float delta, CallbackInfo ci) {
        if (this.handler.slots.size() < 2) {
            // what the f-
            isTradingRightMeow = false;
            return;
        }

        // slot index 2 is always the output slot
        isTradingRightMeow = !this.handler.slots.get(2).getStack().getTranslationKey().equals("block.minecraft.air");
    }

    @Inject(at = @At("HEAD"), method = "drawLevelInfo", cancellable = true)
    public void drawLevelInfo(DrawContext context, int x, int y, TradeOffer tradeOffer, CallbackInfo ci) {
        if (isTradingRightMeow) {
            ci.cancel();
        }
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;"), method = "drawForeground")
    public MutableText drawForeground_translatable(String key, Object[] args) {
        return isTradingRightMeow ? TRADE_OFFER : Text.translatable(key, args);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I", ordinal = 0), method = "drawForeground")
    public int drawText_translatable(DrawContext ctx, TextRenderer textRenderer, Text text, int x, int y, int color, boolean shadow) {
        if (color == 0x404040 && isTradingRightMeow) {
            color = 0xffffff;
        }
        return ctx.drawText(textRenderer, text, x, y, color, shadow);
    }

    @Inject(at = @At("HEAD"), method = "drawForeground")
    public void drawForeground(DrawContext context, int mouseX, int mouseY, CallbackInfo ci) {
        if (isTradingRightMeow) {
            TradeOfferMeow.hotDemonTwinksInHell(context, this.textRenderer, this.backgroundWidth);
        }
    }
}
