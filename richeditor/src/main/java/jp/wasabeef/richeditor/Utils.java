package jp.wasabeef.richeditor;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Copyright (C) 2017 Wasabeef
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public final class Utils {

    private Utils() throws InstantiationException {
        throw new InstantiationException("This class is not for instantiation");
    }

    public static String toBase64(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] bytes = baos.toByteArray();

        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static Bitmap toBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        }

        int width = drawable.getIntrinsicWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getIntrinsicHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }

    public static Bitmap decodeResource(Context context, int resId) {
        return BitmapFactory.decodeResource(context.getResources(), resId);
    }

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static Boolean isKindOfLink(String text) {
        Boolean isLink = false;
        Pattern pattern = Pattern.compile("(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)", Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);
        Matcher myMatcher = pattern.matcher(text);
        while (myMatcher.find()) {
            isLink = true;
            break;
        }
        if (isLink && text.contains("</a>")) {
            isLink = false;
        }
        return isLink;
    }

    public static Integer indexOfNewCharacterAdded(String oldString, String newString) {
        int minLen = Math.min(oldString.length(), newString.length());
        int index = minLen;
        for (int i = 0; i != minLen; i++) {
            char charOld = oldString.charAt(i);
            char charNew = newString.charAt(i);
            if (charOld != charNew) {
                index = i;
                break;
            }
        }
        return index;
    }

    public static String getTextBeforeIndex(String content, int beforeIndex) {
        int indexOfFirst = 0;
        for (int i = beforeIndex - 1; i >= 0; i--) {
            if (content.charAt(i) == ' ') {
                indexOfFirst = i;
                break;
            }

        }
        return content.substring(indexOfFirst, beforeIndex);
    }

    public static String getTextRemoveTextLink(String content, int beforeIndex) {
        int indexOfFirst = 0;
        for (int i = beforeIndex - 1; i >= 0; i--) {
            if (content.charAt(i) == ' ') {
                indexOfFirst = i;
                break;
            }

        }
        String result = content.substring(0, indexOfFirst) + content.substring(beforeIndex, content.length() - 1);
        return result.replaceAll("&nbsp;", " ");
    }
}
