package gay.rooot.tradeoffer.client.mixins;

import gay.rooot.tradeoffer.TradeOfferMeow;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.MerchantMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static gay.rooot.tradeoffer.TradeOfferMeow.TRADE_OFFER_TEXT;

@Mixin(MerchantScreen.class)
public abstract class MerchantScreenMixin extends AbstractContainerScreen<MerchantMenu> {

    @Unique
    private boolean isTradingRightMeow = false;

    public MerchantScreenMixin(MerchantMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Inject(at = @At("HEAD"), method = "extractContents")
    public void renderMain(final GuiGraphicsExtractor graphics, final int mouseX, final int mouseY, final float delta, final CallbackInfo ci) {
        if (this.menu.slots.size() < 3) {
            isTradingRightMeow = false;
            return;
        }

        ItemStack stack = this.menu.slots.get(2).getItem();
        isTradingRightMeow = !stack.isEmpty() && !stack.getItem().getDescriptionId().equals("block.minecraft.air");
    }

    @Inject(at = @At("HEAD"), method = "extractProgressBar", cancellable = true)
    public void drawLevelInfo(GuiGraphicsExtractor graphics, int xo, int yo, MerchantOffer offer, CallbackInfo ci) {
        if (isTradingRightMeow) ci.cancel();
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/network/chat/Component;translatable(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;"), method = "extractLabels")
    public MutableComponent drawForeground_translatable(final String key, final Object[] args) {
        return isTradingRightMeow ? TRADE_OFFER_TEXT : Component.translatable(key, args);
    }

    @Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;text(Lnet/minecraft/client/gui/Font;Lnet/minecraft/network/chat/Component;IIIZ)V", ordinal = 0), method = "extractLabels")
    public void drawText_translatable(GuiGraphicsExtractor graphics, Font font, Component str, int x, int y, int color, boolean dropShadow) {
        if (color == 0xff404040 && isTradingRightMeow) {
            color = 0xffffffff;
        }

        graphics.text(font, str, x, y, color, dropShadow);
    }

    @Inject(at = @At("HEAD"), method = "extractLabels")
    public void extractLabels(GuiGraphicsExtractor graphics, int xm, int ym, CallbackInfo ci) {
        if (isTradingRightMeow) {
            TradeOfferMeow.hotDemonTwinksInHell(graphics, this.font, this.imageWidth /* todo ?? */);
        }
    }
}
