package gay.rooot.tradeoffer;

import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;

public class TradeOfferMeow {
    public static final MutableComponent TRADE_OFFER_TEXT = Component.literal("TRADE OFFER");
    private static final MutableComponent I_RECEIVE_TEXT = Component.literal("i receive:");
    private static final MutableComponent YOU_RECEIVE_TEXT = Component.literal("you receive:");
    private static final MutableComponent TRIANGLE_TEXT = Component.literal("⚠");

    private static final int I_RECEIVE_X = 17;
    private static final int YOU_RECEIVE_X = 89;
    private static final int TRADE_OFFER_X = 49;

    private static final int TEXT_Y = 20;
    private static final int TRIANGLE_Y = 6;

    private static final int TRIANGLE_PADDING = 4;
    private static final int BACKGROUND_PADDING = 3;

    private static final int BACKGROUND_COLOR = 0xffe13d3b;
    private static final int TEXT_COLOR = 0xffffffff;
    private static final int TRIANGLE_COLOR = 0xffffff00;

    public static void hotDemonTwinksInHell(final GuiGraphicsExtractor graphics, final Font font, final int backgroundWidth) {
        int tradeOfferWidth = font.width(TRADE_OFFER_TEXT);
        int tradeOfferX = TRADE_OFFER_X + (backgroundWidth / 2) - (tradeOfferWidth / 2);
        int triangleWidth = font.width(TRIANGLE_TEXT);

        drawCenterText(graphics, font, I_RECEIVE_TEXT, I_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);
        drawCenterText(graphics, font, YOU_RECEIVE_TEXT, YOU_RECEIVE_X, TEXT_Y, backgroundWidth, TEXT_COLOR);

        // no. i am not making it rounded, fuck you. go pr if you want idrc
        graphics.fill(tradeOfferX - triangleWidth - BACKGROUND_PADDING - TRIANGLE_PADDING,
                6 - BACKGROUND_PADDING,
                tradeOfferX + tradeOfferWidth + triangleWidth + BACKGROUND_PADDING + TRIANGLE_PADDING,
                16,
                BACKGROUND_COLOR
        );

        // draw stupid ⚠⚠⚠⚠⚠⚠
        graphics.text(font, TRIANGLE_TEXT, tradeOfferX - triangleWidth - TRIANGLE_PADDING, TRIANGLE_Y, TRIANGLE_COLOR, false);
        graphics.text(font, TRIANGLE_TEXT, tradeOfferX + tradeOfferWidth + TRIANGLE_PADDING + 1, TRIANGLE_Y, TRIANGLE_COLOR, false);
    }

    @SuppressWarnings("SameParameterValue") // yOffset and color gives a warning which we can just...suppress because it's a helper function
    private static void drawCenterText(GuiGraphicsExtractor graphics, Font font, Component text, int xOffset, int yOffset, int backgroundWidth, int color) {
        graphics.text(
                font,
                text,
                xOffset + (backgroundWidth / 2) - (font.width(text) / 2),
                yOffset,
                color,
                true
        );
    }
}
