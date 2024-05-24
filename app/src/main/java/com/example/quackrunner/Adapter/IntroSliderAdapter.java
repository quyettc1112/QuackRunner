package com.example.quackrunner.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.quackrunner.Model.TutorialSlider;
import com.example.quackrunner.R;

import java.util.List;
public class IntroSliderAdapter extends RecyclerView.Adapter<IntroSliderAdapter.IntroSliderViewHolder> {

    private List<TutorialSlider> tutorSliderList;
    private Context context;

    public IntroSliderAdapter(List<TutorialSlider> introSlides, Context context) {
        this.tutorSliderList = introSlides;
        this.context = context;
    }

    @Override
    public IntroSliderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slide_tutorial_slider, parent, false);
        return new IntroSliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IntroSliderViewHolder holder, int position) {
        holder.bind(tutorSliderList.get(position));
    }

    @Override
    public int getItemCount() {
        return tutorSliderList.size();
    }

    public class IntroSliderViewHolder extends RecyclerView.ViewHolder {

        private TextView content;
        private ImageView image;

        public IntroSliderViewHolder(View view) {
            super(view);
            content = view.findViewById(R.id.tv_content);
            image = view.findViewById(R.id.im_slider_image);

        }

        public void bind(TutorialSlider tutorSlider) {
            content.setText(tutorSlider.content.toString());
            image.setImageResource(tutorSlider.image);
        }


    }
}
