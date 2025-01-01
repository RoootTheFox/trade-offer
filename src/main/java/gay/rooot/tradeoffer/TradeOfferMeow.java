package gay.rooot.tradeoffer;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class TradeOfferMeow {
    public static MutableText TRADE_OFFER = Text.translatable("TRADE OFFER");

    // dedicated method for hot reloading meow
    public static void hotDemonTwinksInHell(DrawContext context, TextRenderer textRenderer, int backgroundWidth) {
        var i_get_text = Text.of("i receive:");
        context.drawText(textRenderer, i_get_text, 17 + backgroundWidth / 2 - textRenderer.getWidth(i_get_text) / 2, 20, 0xffffff, true);

        var u_get_text = Text.of("you receive:");
        context.drawText(textRenderer, u_get_text, 89 + backgroundWidth / 2 - textRenderer.getWidth(u_get_text) / 2, 20, 0xffffff, true);

        // draw stupid ⚠⚠⚠⚠⚠⚠
        var trade_offer_w = textRenderer.getWidth(TRADE_OFFER);
        var trade_offer_pos = 49 + backgroundWidth / 2 - trade_offer_w / 2;

        var triangle_dist = 4;
        var bg_thingy = 3;

        var triangle = Text.of("⚠");
        var triangle_w = textRenderer.getWidth(triangle);

        // no. i am not making it rounded, fuck you. go pr if you want idrc
        context.fill(trade_offer_pos - triangle_w - triangle_dist - bg_thingy,
                6 - bg_thingy,
                trade_offer_pos + trade_offer_w + triangle_w + triangle_dist + bg_thingy,
                16, 0xffe13d3b);

        context.drawText(textRenderer, triangle, trade_offer_pos - (triangle_w + triangle_dist), 6, 0xffff00, false);
        context.drawText(textRenderer, triangle, trade_offer_pos + triangle_dist + trade_offer_w, 6, 0xffff00, false);
    }
}
