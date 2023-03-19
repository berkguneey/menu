package com.qr.menu.helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Component
public class QRCodeGeneratorHelper {

    private static final String FILE_FORMAT = "PNG";
    private static final String FILE_PATH = "./src/main/resources/qr-codes/";

    public void generateQRCode(String name, String url, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        Path path = FileSystems.getDefault().getPath(FILE_PATH + name.replaceAll("\\s+", "") + "-QRCode.png");
        MatrixToImageWriter.writeToPath(bitMatrix, FILE_FORMAT, path);
    }

    public byte[] getQRCode(String url, int width, int height) throws WriterException, IOException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);

        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(bitMatrix, FILE_FORMAT, pngOutputStream, new MatrixToImageConfig(0xFF000000, 0xFFFFFFFF));
        return pngOutputStream.toByteArray();
    }

}
