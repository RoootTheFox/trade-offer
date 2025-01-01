package gay.rooot.tradeoffer;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

@SuppressWarnings("SameParameterValue") // Rope.
public class TradeOfferMeow {

    // text stuff
    public static final MutableText TRADE_OFFER_TXT = Text.literal("TRADE OFFER");
    private static final Text I_RECEIVE_TXT = Text.literal("i receive:");
    private static final Text YOU_RECEIVE_TXT = Text.literal("you receive:");
    private static final Text TRIANGLE_TXT = Text.literal("⚠");

    // text positions (x)
    private static final int I_RECEIVE_X = 17;
    private static final int YOU_RECEIVE_X = 89;
    private static final int TRADE_OFFER_X = 49;

    // text positions (y)
    private static final int TEXT_Y = 20;
    private static final int TRIANGLE_Y = 6;

    // padding things
    private static final int TRIANGLE_PADDING = 4;
    private static final int BACKGROUND_PADDING = 3;

    // color stuff
    private static final int BACKGROUND_COLOR = 0xFFE13D3B;
    private static final int TEXT_COLOR = 0xFFFFFF;
    private static final int TRIANGLE_COLOR = 0xFFFF00;

    public static void hotDemonTwinksInHell(final DrawContext ctx, final TextRenderer renderer, final int backgroundWidth) {
        final int tradeOfferWidth = renderer.getWidth(TRADE_OFFER_TXT);
        final int tradeOfferX = TRADE_OFFER_X + (backgroundWidth / 2) - (tradeOfferWidth / 2);
        final int triangleWidth = renderer.getWidth(TRIANGLE_TXT);

        drawCenterText(ctx, renderer, I_RECEIVE_TXT, I_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);
        drawCenterText(ctx, renderer, YOU_RECEIVE_TXT, YOU_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);

        // draw the trade offer background
        ctx.fill(
                tradeOfferX - triangleWidth - BACKGROUND_PADDING - TRIANGLE_PADDING,
                6 - BACKGROUND_PADDING,
                tradeOfferX + tradeOfferWidth + triangleWidth + BACKGROUND_PADDING + TRIANGLE_PADDING,
                16,
                BACKGROUND_COLOR
        );

        // draws the stupid triangles
        ctx.drawText(renderer, TRIANGLE_TXT, tradeOfferX - triangleWidth - TRIANGLE_PADDING, TRIANGLE_Y, TRIANGLE_COLOR, false);
        ctx.drawText(renderer, TRIANGLE_TXT, tradeOfferX + tradeOfferWidth + TRIANGLE_PADDING + 1, TRIANGLE_Y, TRIANGLE_COLOR, false);
    }

    private static void drawCenterText(final DrawContext ctx, final TextRenderer renderer, final Text text, final int xOffset, final int yOffset, final int backgroundWidth, final int color) {
        ctx.drawTextWithShadow(
                renderer,
                text,
                xOffset + (backgroundWidth / 2) - (renderer.getWidth(text) / 2),
                yOffset,
                color
        );
    }

}
