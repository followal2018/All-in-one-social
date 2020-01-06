package com.social_media.ad.classifieds.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter;
import com.social_media.ad.classifieds.R;
import com.social_media.ad.classifieds.model.Child;
import com.social_media.ad.classifieds.model.SectionHeader;
import com.social_media.ad.classifieds.utils.SharedObjects;

import java.util.List;

public class AdapterSectionRecycler extends SectionRecyclerViewAdapter<SectionHeader, Child, AdapterSectionRecycler.SectionViewHolder, AdapterSectionRecycler.ChildViewHolder> {

    Context context;
    SharedObjects sharedObjects;

    public AdapterSectionRecycler(Context context, List<SectionHeader> sectionItemList) {
        super(context, sectionItemList);
        this.context = context;
    }

    @Override
    public SectionViewHolder onCreateSectionViewHolder(ViewGroup sectionViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_header, sectionViewGroup, false);
        return new SectionViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup childViewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.section_child, childViewGroup, false);
        return new ChildViewHolder(view);
    }

    @Override
    public void onBindSectionViewHolder(SectionViewHolder sectionViewHolder, int sectionPosition, SectionHeader section) {

        sectionViewHolder.name.setText(section.getSectionText());

    }

    @Override
    public void onBindChildViewHolder(final ChildViewHolder childViewHolder, int sectionPosition, int childPosition, final Child child) {

        childViewHolder.name.setText(child.getName());
        childViewHolder.icon.setImageResource(child.getIcon());

        if (childViewHolder.name.getText().toString().equals("OOrgin")) {
            childViewHolder.onOff_switch.setVisibility(View.INVISIBLE);
        } else {
            childViewHolder.onOff_switch.setVisibility(View.VISIBLE);

        }

        sharedObjects = new SharedObjects(context);
        Boolean show = sharedObjects.preferencesEditor.getBoolean(child.getKey());

//        Log.e("key--",show?"true  "+child.getKey()+" ":"false  "+child.getKey()+" ");

        childViewHolder.onOff_switch.setChecked(show);

        childViewHolder.onOff_switch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (childViewHolder.onOff_switch.isChecked()) {
                    // do something when check is selected
                    sharedObjects.preferencesEditor.setBoolean(child.getKey(), true);
                } else {
                    //do something when unchecked
                    sharedObjects.preferencesEditor.setBoolean(child.getKey(), false);
                }
            }
        });



    }


    public class SectionViewHolder extends RecyclerView.ViewHolder {

        TextView name;

        public SectionViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.section);
        }
    }

    public class ChildViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        Switch onOff_switch;
        ImageView icon;

        public ChildViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.child);
            onOff_switch = (Switch) itemView.findViewById(R.id.Switch);
            icon = (ImageView) itemView.findViewById(R.id.icon);
        }
    }
}
