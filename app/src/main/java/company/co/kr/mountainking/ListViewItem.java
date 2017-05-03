package company.co.kr.mountainking;

import android.graphics.drawable.Drawable;
import android.widget.TextView;

/**
 * Created by Jaeheon on 2017-04-26.
 */

public class ListViewItem {

    private Drawable iconDrawable;
    private String mt_name;
    private String mt_height;
    private String jungbok;
    private Integer jungbok_count;
    private String speedking;
    private Integer speed_record;
    private String mt_location;


    public void setIcon(Drawable icon) { iconDrawable = icon;}
    public void setMt_name(String name) { mt_name = name;}
    public void setMt_height(String height) { mt_height = height;}
    public void setJungbok(String person) { jungbok = person;}
    public void setJungbok_count(Integer count){ jungbok_count = count;}
    public void setSpeedking(String person) { speedking = person;}
    public void setSpeed_record(Integer record) { speed_record = record;}
    public void setMt_location(String location) { mt_location = location;}


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getMt_name() { return this.mt_name; }
    public String getMt_height() { return this.mt_height;}
    public String getJungbok() {return this.jungbok;}
    public Integer getJungbok_count() {return  this.jungbok_count;}
    public String getSpeedking() { return this.speedking;}
    public Integer getSpeed_record() { return  this.speed_record;}
    public String getMt_location() { return  this.mt_location;}

}
