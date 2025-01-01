package gay.rooot.tradeoffer;

import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;

public class TradeOfferMeow {
    public static final MutableText TRADE_OFFER_TEXT = Text.literal("TRADE OFFER");
    private static final Text I_RECEIVE_TEXT = Text.of("i receive:");
    private static final Text YOU_RECEIVE_TEXT = Text.of("you receive:");
    private static final Text TRIANGLE_TEXT = Text.of("⚠");

    private static final int I_RECEIVE_X = 17;
    private static final int YOU_RECEIVE_X = 89;
    private static final int TRADE_OFFER_X = 49;

    private static final int TEXT_Y = 20;
    private static final int TRIANGLE_Y = 6;

    private static final int TRIANGLE_PADDING = 4;
    private static final int BACKGROUND_PADDING = 3;

    private static final int BACKGROUND_COLOR = 0xffe13d3b;
    private static final int TEXT_COLOR = 0xffffff;
    private static final int TRIANGLE_COLOR = 0xffff00;

    public static void hotDemonTwinksInHell(final DrawContext ctx, final TextRenderer renderer, final int backgroundWidth) {
        int tradeOfferWidth = renderer.getWidth(TRADE_OFFER_TEXT);
        int tradeOfferX = TRADE_OFFER_X + (backgroundWidth / 2) - (tradeOfferWidth / 2);
        int triangleWidth = renderer.getWidth(TRIANGLE_TEXT);

        drawCenterText(ctx, renderer, I_RECEIVE_TEXT, I_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);
        drawCenterText(ctx, renderer, YOU_RECEIVE_TEXT, YOU_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);

        // no. i am not making it rounded, fuck you. go pr if you want idrc
        ctx.fill(tradeOfferX - triangleWidth - BACKGROUND_PADDING - TRIANGLE_PADDING,
                6 - BACKGROUND_PADDING,
                tradeOfferX + tradeOfferWidth + triangleWidth + BACKGROUND_PADDING + TRIANGLE_PADDING,
                16,
                BACKGROUND_COLOR
        );

        // draw stupid ⚠⚠⚠⚠⚠⚠
        ctx.drawText(renderer, TRIANGLE_TEXT, tradeOfferX - triangleWidth - TRIANGLE_PADDING, TRIANGLE_Y, TRIANGLE_COLOR, false);
        ctx.drawText(renderer, TRIANGLE_TEXT, tradeOfferX + tradeOfferWidth + TRIANGLE_PADDING + 1, TRIANGLE_Y, TRIANGLE_COLOR, false);
    }

    @SuppressWarnings("SameParameterValue") // yOffset and color gives a warning which we can just...suppress because it's a helper function
    private static void drawCenterText(DrawContext ctx, TextRenderer renderer, Text text, int xOffset, int yOffset, int backgroundWidth, int color) {
        ctx.drawTextWithShadow(
                renderer,
                text,
                xOffset + (backgroundWidth / 2) - (renderer.getWidth(text) / 2),
                yOffset,
                color
        );
    }
}
