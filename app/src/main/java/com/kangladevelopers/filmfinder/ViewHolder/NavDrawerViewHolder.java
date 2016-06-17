package com.kangladevelopers.filmfinder.ViewHolder;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dilna on 1/15/16.
 */
public class NavDrawerViewHolder {
    ImageView imgIcon;
    TextView txtTitle;

    public NavDrawerViewHolder(ImageView imgIcon, TextView txtTitle) {
        this.imgIcon = imgIcon;
        this.txtTitle = txtTitle;
    }

    public ImageView getImgIcon() {
        return imgIcon;
    }

    public void setImgIcon(ImageView imgIcon) {
        this.imgIcon = imgIcon;
    }

    public TextView getTxtTitle() {
        return txtTitle;
    }

    public void setTxtTitle(TextView txtTitle) {
        this.txtTitle = txtTitle;
    }
}
