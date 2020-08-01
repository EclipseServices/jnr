/*
 * Copyright (c) 2020 Alan Snyder.
 * All rights reserved.
 *
 * You may not use, copy or modify this file, except in compliance with the license agreement. For details see
 * accompanying license terms.
 */

package org.violetlib.jnr.aqua.impl;

import java.awt.geom.Rectangle2D;

import org.violetlib.jnr.Insetter;
import org.violetlib.jnr.LayoutInfo;
import org.violetlib.jnr.aqua.AquaUIPainter;
import org.violetlib.jnr.aqua.AquaUIPainter.PopupButtonWidget;
import org.violetlib.jnr.aqua.AquaUIPainter.SliderWidget;
import org.violetlib.jnr.aqua.ButtonLayoutConfiguration;
import org.violetlib.jnr.aqua.PopupButtonLayoutConfiguration;
import org.violetlib.jnr.aqua.SegmentedButtonLayoutConfiguration;
import org.violetlib.jnr.aqua.SliderLayoutConfiguration;
import org.violetlib.jnr.aqua.TextFieldLayoutConfiguration;
import org.violetlib.jnr.aqua.ToolBarItemWellLayoutConfiguration;
import org.violetlib.jnr.impl.BasicLayoutInfo;
import org.violetlib.jnr.impl.CenteredInsetter1;
import org.violetlib.jnr.impl.CombinedInsetter;
import org.violetlib.jnr.impl.DynamicInsetter1;
import org.violetlib.jnr.impl.FixedInsetter1;
import org.violetlib.jnr.impl.Insetter1;
import org.violetlib.jnr.impl.Insetters;
import org.violetlib.jnr.impl.JNRUtils;

import org.jetbrains.annotations.*;

import static org.violetlib.jnr.aqua.AquaUIPainter.PopupButtonWidget.*;
import static org.violetlib.jnr.impl.JNRUtils.*;

/**
  Layout information for macOS 11+ widgets.
*/

public class BigSurLayoutInfo
  extends ElCapitanLayoutInfo
{
    public BigSurLayoutInfo(boolean isViewBased)
    {
        super(isViewBased);
    }

    @Override
    protected @NotNull LayoutInfo getButtonLayoutInfo(@NotNull ButtonLayoutConfiguration g)
    {
        AquaUIPainter.ButtonWidget bw = g.getButtonWidget();

        if (bw == AquaUIPainter.ButtonWidget.BUTTON_TOOLBAR_ITEM) {
            ToolBarItemWellLayoutConfiguration tg = new ToolBarItemWellLayoutConfiguration();
            return getToolBarItemWellLayoutInfo(tg);
        }

        AquaUIPainter.Size sz = g.getSize();

        if (bw == AquaUIPainter.ButtonWidget.BUTTON_PUSH) {
            // AppKit layout heights are 40, 32, 27, 16
            // The following are visual heights
            return BasicLayoutInfo.createFixedHeight(size(sz, 31, 23, 18, 14));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_BEVEL) {
            return BasicLayoutInfo.getInstance();

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_BEVEL_ROUND) {
            return BasicLayoutInfo.getInstance();

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_CHECK_BOX) {
            return BasicLayoutInfo.createFixed(size(sz, 16, 14, 12, 10), size(sz, 16, 14, 12, 10));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_RADIO) {
            return BasicLayoutInfo.createFixed(size(sz, 18, 16, 14, 10), size(sz, 18, 16, 14, 10));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_DISCLOSURE) {
            return BasicLayoutInfo.createFixed(size(sz, 30, 28, 28, 19), size(sz, 30, 26, 26, 16));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_HELP) {
            return BasicLayoutInfo.createFixed(size(sz, 35, 25, 18, 16), size(sz, 35, 25, 19, 17));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_GRADIENT) {
            return BasicLayoutInfo.getInstance();

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_RECESSED) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 19, 19, 17, 15));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_INLINE) {
            // Note that the NSView does not limit the size, but there seems to be an intended fixed size.
            return BasicLayoutInfo.createFixedHeight(16);

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_ROUNDED_RECT) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 19, 19, 17, 15));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_TEXTURED) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 25, 23, 19, 16));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_TEXTURED_TOOLBAR) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 25, 24, 19, 16));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_ROUND || bw == AquaUIPainter.ButtonWidget.BUTTON_ROUND_TOOLBAR) {
            return BasicLayoutInfo.createFixed(size(sz, 34, 26, 22, 19), size(sz, 34, 26, 22, 19));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_ROUND_INSET) {
            return BasicLayoutInfo.createFixed(18, 18);

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_ROUND_TEXTURED) {
            return BasicLayoutInfo.createFixed(size(sz, 21, 18, 15), size(sz, 22, 19, 16));

//        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_ROUND_TOOLBAR) {
//            return BasicLayoutInfo.createFixed(size(sz, 26, 23, 20, 17), size(sz, 26, 23, 20, 17));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_DISCLOSURE_TRIANGLE) {
            return BasicLayoutInfo.createFixed(13, 13);

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_PUSH_INSET2) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 19, 17, 15));

        } else if (bw == AquaUIPainter.ButtonWidget.BUTTON_COLOR_WELL) {
            return BasicLayoutInfo.createMinimum(44, 23);

        } else {
            return BasicLayoutInfo.getInstance();
        }
    }

    @Override
    protected @NotNull LayoutInfo getSegmentedButtonLayoutInfo(@NotNull SegmentedButtonLayoutConfiguration g)
    {
        AquaUIPainter.SegmentedButtonWidget bw = g.getWidget();
        AquaUIPainter.Size sz = g.getSize();

        switch (bw) {
            case BUTTON_TAB:
            case BUTTON_SEGMENTED:
            case BUTTON_SEGMENTED_SLIDER:
                return BasicLayoutInfo.createFixedHeight(size(sz, 30, 22, 18, 15));

            case BUTTON_SEGMENTED_SEPARATED:
                return BasicLayoutInfo.createFixedHeight(size(sz, 31, 23, 19, 16));

            case BUTTON_SEGMENTED_INSET:
                return BasicLayoutInfo.createFixedHeight(size(sz, 18, 16, 14));

            case BUTTON_SEGMENTED_TEXTURED:
            case BUTTON_SEGMENTED_TEXTURED_TOOLBAR:
            case BUTTON_SEGMENTED_SCURVE:
            case BUTTON_SEGMENTED_TOOLBAR:
                return BasicLayoutInfo.createFixedHeight(size(sz, 20, 16, 13));

            // 20 16 13 for textured, textured toolbar, capsule, toolbar seems wrong

            case BUTTON_SEGMENTED_TEXTURED_SEPARATED:
            case BUTTON_SEGMENTED_TEXTURED_SEPARATED_TOOLBAR:
                return BasicLayoutInfo.createFixedHeight(size(sz, 23, 19, 16));

            case BUTTON_SEGMENTED_SMALL_SQUARE:
                return BasicLayoutInfo.createFixedHeight(size(sz, 21, 19, 17));

            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override
    public @NotNull Insetter getPopupButtonContentInsets(@NotNull PopupButtonLayoutConfiguration g)
    {
        PopupButtonWidget bw = g.getPopupButtonWidget();
        if (bw == BUTTON_POP_UP || bw == BUTTON_POP_DOWN) {
            AquaUIPainter.Size sz = g.getSize();
            float top = 1;
            float bottom = 1;
            float far = 3;
            float near = size2D(sz, 21, 17, 14);

            switch (bw) {
                case BUTTON_POP_UP:
                default:
                    bottom = size2D(sz, 2.5f, 2.5f, 2);
                    top = size2D(sz, 0.5f, 0.5f, 1);
                    break;
                case BUTTON_POP_DOWN:
                    bottom = 2;
                    break;
            }
            LayoutInfo layoutInfo = getLayoutInfo(g);
            return g.isLeftToRight()
                     ? Insetters.createFixed(top, far, bottom, near, layoutInfo)
                     : Insetters.createFixed(top, near, bottom, far, layoutInfo);
        }
        return super.getPopupButtonContentInsets(g);
    }

    @Override
    protected @NotNull LayoutInfo getTextFieldLayoutInfo(@NotNull TextFieldLayoutConfiguration g)
    {
        AquaUIPainter.TextFieldWidget w = g.getWidget();
        if (w.isRound() || w.isSearch()) {
            if (w.isToolbar()) {
                // The actual sizes for small and mini are bogus. We do not simulate this bug.
                return BasicLayoutInfo.createFixedHeight(size(g.getSize(), 30, 30, 20, 17));
            } else {
                return BasicLayoutInfo.createFixedHeight(size(g.getSize(), 30, 22, 19, 17));
            }
        }

        return BasicLayoutInfo.getInstance();
    }

    @Override
    protected @NotNull LayoutInfo getSliderLayoutInfo(@NotNull SliderLayoutConfiguration g)
    {
        // macOS 11 introduced new linear slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (!g.isLinear() || AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getSliderLayoutInfo(g);
        }

        return getLinearSlider11LayoutInfo(g);
    }

    @Override
    protected double getSliderExtension(@NotNull AquaUIPainter.Size sz)
    {
        switch (sz)
        {
            case SMALL:
            case MINI:
                return 6.5;

            default:
                return 10;
        }
    }

    private @NotNull LayoutInfo getLinearSlider11LayoutInfo(@NotNull SliderLayoutConfiguration g)
    {
        AquaUIPainter.Size sz = g.getSize();

        // These numbers are tentative.

        if (g.isHorizontal()) {
            return BasicLayoutInfo.createFixedHeight(size(sz, 24, 20, 20));
        } else if (g.isVertical()) {
            return BasicLayoutInfo.createFixedWidth(size(sz, 24, 20, 20));
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public @NotNull Insetter getSliderTrackPaintingInsets(@NotNull SliderLayoutConfiguration g)
    {
        // macOS 11 introduced new slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getSliderTrackPaintingInsets(g);
        }

        return getSlider11TrackPaintingInsets(g);
    }

    private @NotNull Insetter getSlider11TrackPaintingInsets(@NotNull SliderLayoutConfiguration g)
    {
        AquaUIPainter.Size sz = g.getSize();
        float trackWidth = JNRUtils.size(sz, 4, 4, 3);
        float majorInset = JNRUtils.size(sz, 10, 8, 6);
        boolean isCircle = !g.hasTickMarks();
        if (isCircle) {
            majorInset += 2;
        }

        Insetter1 horizontal;
        Insetter1 vertical;
        if (g.isHorizontal()) {
            horizontal = new FixedInsetter1(majorInset, majorInset);
            vertical = CenteredInsetter1.createCentered(trackWidth, 0);
        } else if (g.isVertical()) {
            vertical = new FixedInsetter1(majorInset, majorInset);
            horizontal = CenteredInsetter1.createCentered(trackWidth, 0);
        } else {
            // not meaningful
            horizontal = new FixedInsetter1(0, 0);
            vertical = new FixedInsetter1(0, 0);
        }
        return new CombinedInsetter(horizontal, vertical);
    }

    @Override
    protected @NotNull LayoutInfo getSliderThumbLayoutInfo(@NotNull SliderLayoutConfiguration g, boolean isForPainting)
    {
        // macOS 11 introduced new linear slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (!g.isLinear() || AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getSliderThumbLayoutInfo(g, isForPainting);
        }

        return getSlider11ThumbLayoutInfo(g, isForPainting);
    }

    private @NotNull LayoutInfo getSlider11ThumbLayoutInfo(@NotNull SliderLayoutConfiguration g, boolean isForPainting)
    {
        // The outline size may be smaller because it excludes a shadow.

        AquaUIPainter.Size sz = g.getSize();
        if (g.hasTickMarks()) {
            float width;
            float height;
            if (g.isHorizontal()) {
                width = size(sz, 13, 12, 11);
                height = size(sz, 24, 20, 17);
                if (!isForPainting) {
                    height += -1;
                }
            } else {
                width = size(sz, 24, 20, 17);
                height = size(sz, 13, 12, 11);
            }
            return BasicLayoutInfo.createFixed(width, height);
        } else {
            float width = size(sz, 24, 20, 20);
            float height = isForPainting ? width : width - 1;
            return BasicLayoutInfo.createFixed(width, height);
        }
    }

    @Override
    protected @NotNull Insetter getSliderThumbInsets(@NotNull SliderLayoutConfiguration g,
                                                     double thumbPosition,
                                                     boolean isForPainting)
    {
        // macOS 11 introduced new linear slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (!g.isLinear() || AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getSliderThumbInsets(g, thumbPosition, isForPainting);
        }

        return getSlider11ThumbInsets(g, thumbPosition, isForPainting);
    }

    private @NotNull Insetter getSlider11ThumbInsets(@NotNull SliderLayoutConfiguration g,
                                                     double thumbPosition,
                                                     boolean isForPainting)
    {
        LayoutInfo layoutInfo = getSliderThumbLayoutInfo(g, isForPainting);
        LayoutInfo visualLayoutInfo = isForPainting ? getSliderThumbLayoutInfo(g, false) : layoutInfo;

        // For alignment purposes, we want the layout that corresponds to the outline, not the one that
        // describes the full rendering including shadows.

        if (g.isHorizontal()) {
            float offset = getPointerOffset(g);
            Insetter1 vertical = CenteredInsetter1.createCentered(layoutInfo.getFixedVisualHeight(), visualLayoutInfo.getFixedVisualHeight(), offset);
            Insetter1 horizontal = new DynamicInsetter1(layoutInfo.getFixedVisualWidth())
            {
                @Override
                protected float getCenterPosition(float componentSize)
                {
                    Rectangle2D bounds = new Rectangle2D.Float(0, 0, componentSize, 30);
                    return (float) getSlider11ThumbCenter(bounds, g, thumbPosition);
                }
            };
            return new CombinedInsetter(horizontal, vertical);
        } else {
            float offset = getPointerOffset(g);
            Insetter1 horizontal = CenteredInsetter1.createCentered(layoutInfo.getFixedVisualWidth(), visualLayoutInfo.getFixedVisualWidth(), offset);
            Insetter1 vertical = new DynamicInsetter1(layoutInfo.getFixedVisualHeight())
            {
                @Override
                protected float getCenterPosition(float componentSize)
                {
                    Rectangle2D bounds = new Rectangle2D.Float(0, 0, 30, componentSize);
                    return (float) getSlider11ThumbCenter(bounds, g, thumbPosition);
                }
            };
            return new CombinedInsetter(horizontal, vertical);
        }
    }

    /**
      Determine a position adjustment along the minor axis for painting linear slider tick marks.
    */

    protected float getPointerOffset(@NotNull SliderLayoutConfiguration g)
    {
        // macOS 11 introduced new linear slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (!g.isLinear() || AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getPointerOffset(g);
        }

        return 0;
    }

    @Override
    public double getSliderThumbCenter(@NotNull Rectangle2D bounds,
                                       @NotNull SliderLayoutConfiguration g,
                                       double thumbPosition)
    {
        // macOS 11 introduced new linear slider styles with different layout properties. However, the NSView renderer
        // may or may not use the new style, based on runtime determined linkage information.

        if (!g.isLinear() || AquaUIPainterBase.internalGetSliderRenderingVersion() == AquaUIPainterBase.SLIDER_10_10) {
            return super.getSliderThumbCenter(bounds, g, thumbPosition);
        }

        return getSlider11ThumbCenter(bounds, g, thumbPosition);
    }

    private double getSlider11ThumbCenter(@NotNull Rectangle2D bounds,
                                          @NotNull SliderLayoutConfiguration g,
                                          double thumbPosition)
    {
        SliderWidget sw = g.getWidget();
        AquaUIPainter.Size sz = g.getSize();

        double left = bounds.getX();
        double top = bounds.getY();
        double w = bounds.getWidth();
        double h = bounds.getHeight();

        // This method is given the track painting bounds rather than the full layout bounds.
        // An extension may not be needed.

        double extension = 0; // getSliderExtension(sz);

        if (sw == SliderWidget.SLIDER_HORIZONTAL_RIGHT_TO_LEFT) {
            thumbPosition = 1 - thumbPosition;
            sw = SliderWidget.SLIDER_HORIZONTAL;
        } else if (sw == SliderWidget.SLIDER_VERTICAL) {
            thumbPosition = 1 - thumbPosition;
            sw = SliderWidget.SLIDER_UPSIDE_DOWN;
        }

        if (sw == SliderWidget.SLIDER_HORIZONTAL) {
            double width = w - 2 * extension;
            return left + extension + thumbPosition * width;

        } else if (sw == SliderWidget.SLIDER_UPSIDE_DOWN) {
            double height = h - 2 * extension;
            return top + extension + thumbPosition * height;

        } else {
            return 0;
        }
    }

    @Override
    public @NotNull Insetter getSliderTickMarkPaintingInsets(@NotNull SliderLayoutConfiguration g)
    {
        int style = AquaUIPainterBase.internalGetSliderRenderingVersion();
        if (style == AquaUIPainterBase.SLIDER_11_0 && g.isLinear() && g.hasTickMarks()) {
            float FAKE_LENGTH = 100;
            LayoutInfo sliderLayout = getSliderLayoutInfo(g);
            LayoutInfo tickLayout = getSliderTickLayoutInfo(g);
            Insetter trackInsets = getSliderTrackPaintingInsets(g);
            if (g.isHorizontal()) {
                float sliderHeight = sliderLayout.getFixedVisualHeight();
                Rectangle2D trackBounds = trackInsets.apply2D(FAKE_LENGTH, sliderHeight);
                float trackHeight = (float) trackBounds.getHeight();
                float tickHeight = tickLayout.getFixedVisualHeight();
                float trackOffset = (float) trackBounds.getY();
                float tickTopOffset = trackOffset - (tickHeight - trackHeight) / 2;
                float tickBottomOffset = sliderHeight - (tickTopOffset + tickHeight);
                float trackLeftInset = (float) trackBounds.getX();
                float trackRightInset = (float) (FAKE_LENGTH - (trackLeftInset + trackBounds.getWidth()));
                return new CombinedInsetter(new FixedInsetter1(trackLeftInset, trackRightInset),
                  new FixedInsetter1(tickTopOffset, tickBottomOffset));
            } else {
                float sliderWidth = sliderLayout.getFixedVisualWidth();
                Rectangle2D trackBounds = trackInsets.apply2D(sliderWidth, FAKE_LENGTH);
                float trackWith = (float) trackBounds.getWidth();
                float tickWidth = tickLayout.getFixedVisualWidth();
                float trackOffset = (float) trackBounds.getX();
                float tickLeftOffset = trackOffset - (tickWidth - trackWith) / 2;
                float tickRightOffset = sliderWidth - (tickLeftOffset + tickWidth);
                float trackTopInset = (float) trackBounds.getY();
                float trackBottomInset = (float) (FAKE_LENGTH - (trackTopInset + trackBounds.getHeight()));
                return new CombinedInsetter(new FixedInsetter1(tickLeftOffset, tickRightOffset),
                  new FixedInsetter1(trackTopInset, trackBottomInset));
            }
        }

        return super.getSliderTickMarkPaintingInsets(g);
    }
}
