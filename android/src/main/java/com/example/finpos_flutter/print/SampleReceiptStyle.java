package com.example.finpos_flutter.print;


import global.citytech.finpossmart.sdk.api.printer.Style;


public enum SampleReceiptStyle {

    LARGE(Style.FontSize.LARGE, Style.Align.CENTER, 1,
            false, false, false, false),
    LARGE_BOLD(Style.FontSize.LARGE, Style.Align.CENTER, 1,
            false, true, false, false),
    LARGE_ITALIC(Style.FontSize.LARGE, Style.Align.CENTER, 1,
            false, false, true, false),
    NORMAL(Style.FontSize.NORMAL, Style.Align.LEFT, 1,
            false, false, false, false),
    NORMAL_BOLD(Style.FontSize.NORMAL, Style.Align.LEFT, 1,
            false, true, false, false),
    SMALL(Style.FontSize.SMALL, Style.Align.LEFT, 1,
            false, false, false, false),
    NORMAL_ITALIC(Style.FontSize.NORMAL, Style.Align.LEFT, 2,
            false, false, true, false);

    private Style.FontSize fontSize;
    private Style.Align alignment;
    private int columns;
    private boolean allCaps;
    private boolean bold;
    private boolean italic;
    private boolean underline;

    SampleReceiptStyle(
            Style.FontSize fontSize,
            Style.Align alignment,
            int columns,
            boolean allCaps,
            boolean bold,
            boolean italic,
            boolean underline
    ) {
        this.fontSize = fontSize;
        this.alignment = alignment;
        this.columns = columns;
        this.allCaps = allCaps;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
    }

    public Style getStyle() {
        return new Style.Builder()
                .fontSize(this.fontSize)
                .alignment(this.alignment)
                .multipleAlignment(this.ifMultipleAlignment())
                .bold(this.bold)
                .italic(this.italic)
                .underline(this.underline)
                .allCaps(this.allCaps)
                .build();
    }

    public int[] getColumnWidths() {
        int num = 100 / this.columns;
        int[] columnWidths = new int[this.columns];
        for (int i = 0; i < this.columns; i++) {
            columnWidths[i] = num;
        }
        return columnWidths;
    }

    private boolean ifMultipleAlignment() {
        return this.columns > 1;
    }
}

