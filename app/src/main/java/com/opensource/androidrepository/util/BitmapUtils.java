package com.opensource.androidrepository.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Various bitmap utilities
 * 
 */
public class BitmapUtils {

	/**
	 * Resize a bitmap
	 * 
	 * @param input
	 * @param destWidth
	 * @param destHeight
	 * @return
	 * @throws OutOfMemoryError
	 */
	public static Bitmap resizeBitmap(final Bitmap input, int destWidth, int destHeight ) throws OutOfMemoryError {
		return resizeBitmap( input, destWidth, destHeight, 0 );
	}

	/**
	 * Resize a bitmap object to fit the passed width and height
	 * 
	 * @param input
	 *           The bitmap to be resized
	 * @param destWidth
	 *           Desired maximum width of the result bitmap
	 * @param destHeight
	 *           Desired maximum height of the result bitmap
	 * @return A new resized bitmap
	 * @throws OutOfMemoryError
	 *            if the operation exceeds the available vm memory
	 */
	public static Bitmap resizeBitmap(final Bitmap input, int destWidth, int destHeight, int rotation ) throws OutOfMemoryError {

		int dstWidth = destWidth;
		int dstHeight = destHeight;
		final int srcWidth = input.getWidth();
		final int srcHeight = input.getHeight();

		if ( rotation == 90 || rotation == 270 ) {
			dstWidth = destHeight;
			dstHeight = destWidth;
		}

		boolean needsResize = false;
		float p;
		if ( ( srcWidth > dstWidth ) || ( srcHeight > dstHeight ) ) {
			needsResize = true;
			if ( ( srcWidth > srcHeight ) && ( srcWidth > dstWidth ) ) {
				p = (float) dstWidth / (float) srcWidth;
				dstHeight = (int) ( srcHeight * p );
			} else {
				p = (float) dstHeight / (float) srcHeight;
				dstWidth = (int) ( srcWidth * p );
			}
		} else {
			dstWidth = srcWidth;
			dstHeight = srcHeight;
		}

		if ( needsResize || rotation != 0 ) {
			Bitmap output;

			if ( rotation == 0 ) {
				output = Bitmap.createScaledBitmap(input, dstWidth, dstHeight, true);
			} else {
				Matrix matrix = new Matrix();
				matrix.postScale( (float) dstWidth / srcWidth, (float) dstHeight / srcHeight );
				matrix.postRotate( rotation );
				output = Bitmap.createBitmap(input, 0, 0, srcWidth, srcHeight, matrix, true);
			}
			return output;
		} else
			return input;
	}

    public static void saveBitmap(Bitmap mBitmap, String dir, String fileName) {

        String savePath = "";
        // 判断是否挂载了SD卡
        String storageState = Environment.getExternalStorageState();
        if (storageState.equals(Environment.MEDIA_MOUNTED)) {
            savePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/RecordVideo/" + dir;// 存放照片的文件夹
            File savedir = new File(savePath);
            if (!savedir.exists()) {
                savedir.mkdirs();
            }
        }

        // 没有挂载SD卡，无法保存文件
        if (isEmpty(savePath)) {
//            Toast.makeText(context, "无法保存照片，请检查SD卡是否挂载", Toast.LENGTH_SHORT).show();
            return;
        }

        File f = new File(savePath, fileName);
        FileOutputStream fOut = null;

        try {
            fOut = new FileOutputStream(f);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
            mBitmap.recycle();
            fOut.flush();
            fOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isEmpty(String input) {
        if (input == null || "".equals(input))
            return true;

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c != ' ' && c != '\t' && c != '\r' && c != '\n') {
                return false;
            }
        }
        return true;
    }

    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(outBitmap);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPX = bitmap.getWidth() / 2;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return outBitmap;
    }

    /**
     * 缩放图片
     *
     * @param bitmap
     * @param zf
     * @return
     */
    public static Bitmap zoom(Bitmap bitmap, float zf) {
        Matrix matrix = new Matrix();
        matrix.postScale(zf, zf);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * 缩放图片
     *
     * @param bitmap
     * @param hf
     * @return
     */
    public static Bitmap zoom(Bitmap bitmap, float wf, float hf) {
        Matrix matrix = new Matrix();
        matrix.postScale(wf, hf);
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
    }

    /**
     * 图片圆角处理
     *
     * @param bitmap
     * @param roundPX
     * @return
     */
    public static Bitmap getRCB(Bitmap bitmap, float roundPX) {
        // RCB means
        // Rounded
        // Corner Bitmap
        Bitmap dstbmp = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(dstbmp);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPX, roundPX, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return dstbmp;
    }

    public static Bitmap getBitmapFromFilePath(String filePath) {
        try {
            return BitmapFactory.decodeFile(filePath);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmapFromFilePath(String filePath, int width, int height) {
        BitmapFactory.Options opts = null;
        if (width > 0 && height > 0) {
            opts = new BitmapFactory.Options();
            opts.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, opts);
            // 计算图片缩放比例
            final int minSideLength = Math.min(width, height);
            opts.inSampleSize = computeSampleSize(opts, minSideLength,
                    width * height);
            opts.inJustDecodeBounds = false;
            opts.inInputShareable = true;
            opts.inPurgeable = true;
        }
        try {
            return BitmapFactory.decodeFile(filePath, opts);
        } catch (OutOfMemoryError e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Bitmap getBitmapFromFile(File dst, int width, int height) {
        if (null != dst && dst.exists()) {
            BitmapFactory.Options opts = null;
            if (width > 0 && height > 0) {
                opts = new BitmapFactory.Options();
                opts.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(dst.getPath(), opts);
                // 计算图片缩放比例
                final int minSideLength = Math.min(width, height);
                opts.inSampleSize = computeSampleSize(opts, minSideLength,
                        width * height);
                opts.inJustDecodeBounds = false;
                opts.inInputShareable = true;
                opts.inPurgeable = true;
            }
            try {
                return BitmapFactory.decodeFile(dst.getPath(), opts);
            } catch (OutOfMemoryError e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int computeSampleSize(BitmapFactory.Options options,
                                        int minSideLength, int maxNumOfPixels) {
        int initialSize = computeInitialSampleSize(options, minSideLength,
                maxNumOfPixels);

        int roundedSize;
        if (initialSize <= 8) {
            roundedSize = 1;
            while (roundedSize < initialSize) {
                roundedSize <<= 1;
            }
        } else {
            roundedSize = (initialSize + 7) / 8 * 8;
        }

        return roundedSize;
    }

    private static int computeInitialSampleSize(BitmapFactory.Options options,
                                                int minSideLength, int maxNumOfPixels) {
        double w = options.outWidth;
        double h = options.outHeight;

        int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
                .sqrt(w * h / maxNumOfPixels));
        int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math
                .floor(w / minSideLength), Math.floor(h / minSideLength));

        if (upperBound < lowerBound) {
            // return the larger one when there is no overlapping zone.
            return lowerBound;
        }

        if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
            return 1;
        } else if (minSideLength == -1) {
            return lowerBound;
        } else {
            return upperBound;
        }
    }

    public static Bitmap addPadding(Bitmap bmp, int color) {

        if (bmp == null) {
            return null;
        }

        int biggerParam = Math.max(bmp.getWidth(), bmp.getHeight());
        Bitmap bitmap = Bitmap.createBitmap(biggerParam, biggerParam, bmp.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(color);

        int top = bmp.getHeight() > bmp.getWidth() ? 0 : (bmp.getWidth() - bmp.getHeight())/2;
        int left = bmp.getWidth() > bmp.getHeight() ? 0 : (bmp.getHeight() - bmp.getWidth())/2;

        canvas.drawBitmap(bmp, left, top, null);
        return bitmap;
    }

    public static byte[] bmpToByteArray(final Bitmap bmp, final boolean needRecycle) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, output);
        if (needRecycle) {
            bmp.recycle();
        }

        byte[] result = output.toByteArray();
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
