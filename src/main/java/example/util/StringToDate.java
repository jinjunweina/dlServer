package example.util;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by jinjw on 2017/7/24.
 */
public class StringToDate implements Converter<String, Date>{
    private static final String dateFormat = "yyyy-MM-dd HH:mm:ss";
    private static final String shortDateFormat = "yyyy-MM-dd";

    @Override
    public Date convert(String source) {
        SimpleDateFormat simpleDateFormat;

        if(StringUtils.isEmpty(source)){
            return null;
        }
        source = source.trim();
        if (source.contains("-")){
            if (source.contains(":")){
                simpleDateFormat = new SimpleDateFormat(dateFormat);
            }else {
                simpleDateFormat = new SimpleDateFormat(shortDateFormat);
            }
            Date date = new Date();
            try {
                 date = simpleDateFormat.parse(source);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return date;
        }else if(source.matches("^\\d+$")){
             return new Date(new Long(source));
        }else {
            return  null;
        }
//        return simpleDateFormat.parse(source);
    }
}
