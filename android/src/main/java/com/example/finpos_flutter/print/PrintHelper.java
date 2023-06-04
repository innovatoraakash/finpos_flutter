package com.example.finpos_flutter.print;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import global.citytech.finpossmart.sdk.api.printer.PrintBase64Image;
import global.citytech.finpossmart.sdk.api.printer.PrintMessage;
import global.citytech.finpossmart.sdk.api.printer.PrintRequest;
import global.citytech.finpossmart.sdk.api.printer.Printable;

public class PrintHelper {

    public static PrintRequest preparePrintRequest(Context context) {
        List<Printable> printMessages = new ArrayList<>();
        addPrintContent(printMessages, context);
        return PrintRequest.Builder.newInstance(printMessages).build();
    }

    private static void addPrintContent(List<Printable> printables, Context context) {
        printables.add(PrintMessage.getInstance("### Image Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
//        Bitmap icon = BitmapFactory.decodeResource(context.getResources(), R.drawable.receipt_header_citytech);
//        printables.add(PrintBitmap.getInstance(icon));

        printables.add(PrintMessage.getInstance("", SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("### Single Text Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
        printables.add(PrintMessage.getInstance("FinPos Smart SDK", SampleReceiptStyle.LARGE_BOLD.getStyle()));
        printables.add(PrintMessage.getInstance("Citytech Global", SampleReceiptStyle.LARGE_ITALIC.getStyle()));
        printables.add(PrintMessage.getInstance("सिटीटेक ग्लोबल", SampleReceiptStyle.LARGE_BOLD.getStyle()));

        printables.add(PrintMessage.getInstance("", SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("### Double Text Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
        printables.add(PrintMessage.getDoubleColumnInstance("Address ", "Kamaladi, Kathmandu", SampleReceiptStyle.SMALL.getStyle()));
        printables.add(PrintMessage.getDoubleColumnInstance("2022-02-03", "05:59 PM", SampleReceiptStyle.SMALL.getStyle()));

        printables.add(PrintMessage.getInstance("", SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("### Multi Column Text Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
        List<String> strings = new ArrayList<>();
        strings.add("SN");
        strings.add("Device");
        strings.add("Qty");
        strings.add("Spec");
        int[] columnWidths = {10, 30, 30, 30};
        printables.add(PrintMessage.getMultiColumnInstance(strings, columnWidths, SampleReceiptStyle.NORMAL.getStyle()));

        strings = new ArrayList<>();
        strings.add("1");
        strings.add("FinPos");
        strings.add("1");
        strings.add("2 GB");
        printables.add(PrintMessage.getMultiColumnInstance(strings, columnWidths, SampleReceiptStyle.NORMAL.getStyle()));

        strings = new ArrayList<>();
        strings.add("2");
        strings.add("Tablet");
        strings.add("2");
        strings.add("2 GB");
        printables.add(PrintMessage.getMultiColumnInstance(strings, columnWidths, SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("", SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("### QR Code Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
//        printables.add(
//                PrintMessage.getQRCodeInstance("\"FinPos\"  is dream to connect millions of people for secure, sustainable, and splendid financial events with the emerging technology",
//                200,
//                Style.Align.CENTER
//                )
//        );

        printables.add(PrintMessage.getInstance("", SampleReceiptStyle.NORMAL.getStyle()));

        printables.add(PrintMessage.getInstance("### Base64 Image Printing ###", SampleReceiptStyle.NORMAL.getStyle()));
        printables.add(PrintBase64Image.getInstance("iVBORw0KGgoAAAANSUhEUgAAADAAAAAwCAYAAABXAvmHAAADa0lEQVR42u1Z30tTURy3ZpEJvdhLUEFBEVl/gPhYPva4R5/8ByKI0qC2Xha+VBii5YOJDQoUf0BJSsspm7hNU2zRHPPHrKZ3P+5+EDMcp/MZXbm73bt7J2znDDzw4e5uV8/nc873+znfe05NzWHjuBFCTBS1jGE6CPGjAEcDaZyP/EH6uYHiPMU5RkDfDWrcipKn15MU3SuJbOTlt3jMsrgd65iPVBToE30Hk9lILpfrBiddEdKPe3t7PZNbGWLx7ZDx9RRZFbNMgL7BYTKcJqIovqLUjmgKkJIllUqdXhZ+7+APw+nsLlWfo4KYAA0cLAsCWfoZE5xO5xk5V6WAWlz9fv+l3pVoAur//RPCEuAwvpEmL3xb4sjIyFU5V1UBMzMzl62+7TimkBcBweQueeBaF+12e6MhAe3zkQRvAu4618SBgYFrzAQI0Uz1Cvi+KpDb9yZI32tfyUKYC8hkdon1iSMvALj/aKokEcwFvP8Y2CefF/BwKi+qKgRshsUC8oB7Plw9IdTVM1dAHvdVlcRWm6MgdOSxPzz61VBSc5MDuCpdSUpqhzPEXkBfv5d09c6pjqjyO/nMAG/eLrEV8GX5V8GIfpgMaD77aTpUkiuVXYDS5yV0PpvNu5Da8xhx6TkIYpoDSEYleTmGx/yqI4w8KBY6FRGg5vNqwAwhzLirhdR8vvPprKYQrmoh5ejLfR6WiXs1EXq2WTEByhpHGc8Qo5yhgtmilquW5BUTIHcSAEmpZbFaswHoFXZlE4CFS05EvtLqWee+Q1EH4zaE9OwWq7GRsrpsAuSrr14YyWsfCUZttaw2qqxppDJCGllccf+fndLw42IdwMuJkYWM6zcyteQsBq0wY1qN6tVDUtIa8X1m7wMgh9lQej5KC4RaKWHD5cZW1e/MVVSAhcPN3Q6jm7sul+sKr9vrg4OD14sJyB8aDA0NnV3ezkStCwI3BxwdXoEsbEZiNpvtIjg6HI7aYkdMJwKBwLuJDZE8XowSqA+l/zAB+gb5ifUE8Xq9w5RbnZEzMlNzc3Ojx+MZ9YS24s89G8n26aB453MwWUmgT/TtXfsRd7vdY01NTQgfk9GTyjqKCy0tLTfa2tputba2MgH6vkkbuOiOvlKE2Ww+Tj/WU5xijHpwOdDBO5KFxt4xltBM2MPGSfsLR8WTg7cH7GwAAAAASUVORK5CYII="));
    }
}
