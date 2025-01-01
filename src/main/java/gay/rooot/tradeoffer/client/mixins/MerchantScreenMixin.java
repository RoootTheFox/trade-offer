package gay.rooot.tradeoffer.client.mixins;

import gay.rooot.tradeoffer.TradeOfferMeow;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.gui.screen.ingame.MerchantScreen;
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

import static gay.rooot.tradeoffer.TradeOfferMeow.TRADE_OFFER_TXT;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin extends HandledScreen<MerchantScreenHandler> {

    @Unique
    private boolean isTradingRightMeow = false;

    public MerchantScreenMixin(MerchantScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Inject(at = @At("HEAD"), method = "render")
    public void render(final DrawContext context, final int mouseX, final int mouseY, final float delta, final CallbackInfo ci) {
        if (this.handler.slots.size() < 2) {
            // what the f-
            isTradingRightMeow = false;
            return;
        }

        // slot index 2 is always the output slot
        isTradingRightMeow = !this.handler.slots.get(2).getStack().getTranslationKey().equals("block.minecraft.air");
    }

    @Inject(at = @At("HEAD"), method = "drawLevelInfo", cancellable = true)
    public void drawLevelInfo(final DrawContext context, final int x, final int y, final TradeOffer tradeOffer, final CallbackInfo ci) {
        if (isTradingRightMeow) ci.cancel();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/text/Text;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/text/MutableText;"), method = "drawForeground")
    public MutableText drawForeground_translatable(final String key, final Object[] args) {
        return isTradingRightMeow ? TRADE_OFFER_TXT : Text.translatable(key, args);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;drawText(Lnet/minecraft/client/font/TextRenderer;Lnet/minecraft/text/Text;IIIZ)I", ordinal = 0), method = "drawForeground")
    public int drawText_translatable(final DrawContext ctx, final TextRenderer textRenderer, final Text text, final int x, final int y, int color, final boolean shadow) {
        if (color == 0x404040 && isTradingRightMeow) {
            color = 0xffffff;
        }

        return ctx.drawText(textRenderer, text, x, y, color, shadow);
    }

    @Inject(at = @At("HEAD"), method = "drawForeground")
    public void drawForeground(final DrawContext context, final int mouseX, final int mouseY, final CallbackInfo ci) {
        if (isTradingRightMeow) {
            TradeOfferMeow.renderStupidMeme(context, this.textRenderer, this.backgroundWidth);
        }
    }

}
