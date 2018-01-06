package my.edu.tarc.staff.Model;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import my.edu.tarc.staff.R;


/**
 * Created by TARC on 8/6/2015.
 */
public class SubjectAdapter extends ArrayAdapter<Subject> {

    public SubjectAdapter(Activity context, int resource, List<Subject> list) {
        super(context, resource, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Subject subject = getItem(position);

        LayoutInflater inflater  = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.subject_record, parent, false);

        TextView textViewCode, textViewTitle, textViewDescription;

        textViewCode = (TextView)rowView.findViewById(R.id.textViewCode);
        textViewTitle = (TextView)rowView.findViewById(R.id.textViewTitle);
        textViewDescription = (TextView)rowView.findViewById(R.id.textViewDescription);

        textViewCode.setText(textViewCode.getText() + ":" +subject.getCode());
        textViewTitle.setText(textViewTitle.getText() + ":" + subject.getTitle());
        textViewDescription.setText(textViewDescription.getText() + ":" + subject.getDescription());
        return rowView;
    }
}
