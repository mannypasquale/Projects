package ca.camosun.realfinal;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.util.Log;
import ca.camosun.realfinal.dummy.Courses;
/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {



    EditText cName;
    EditText cID;
    EditText cProf;
    EditText cDetails;

    TextView cNameView;
    TextView cIDView;
    TextView cProfView;
    TextView cDetailsView;

    Button saveDetesButton;

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */



    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Courses.CourseItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = Courses.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.id);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);

        this.cDetails = (EditText) rootView.findViewById(R.id.CourseDetailsEdit);
        this.cName = (EditText) rootView.findViewById(R.id.CourseNameEdit);
        this.cID = (EditText) rootView.findViewById(R.id.CourseIDEdit);
        this.cProf = (EditText) rootView.findViewById(R.id.CourseProfEdit);
        this.saveDetesButton = (Button) rootView.findViewById(R.id.saveDetailsButton);

        try{
            if (mItem.hasBeenEditited == true){
                cName.setText(mItem.courseName);
                cID.setText(mItem.courseID);
                cProf.setText(mItem.courseProf);
                cDetails.setText(mItem.courseDetails);
            }
        } catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
        try{
            if (mItem.hasBeenEditited == false){
                cName.setText("Enter Name");
                cID.setText("Enter ID");
                cProf.setText("Enter Prof");
                cDetails.setText("Small Details!");

            }
            }catch (Exception e){
            Log.d("ERROR", e.getMessage());
        }
        this.saveDetesButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String cnameChange = cName.getText().toString();
                String cIDChange = cID.getText().toString();
                String cProfChange = cProf.getText().toString();
                String cDetailsChange = cDetails.getText().toString();
                try{
                    mItem.courseName = cnameChange;
                    mItem.courseID = cIDChange;
                    mItem.courseProf = cProfChange;
                    mItem.courseDetails = cDetailsChange;
                    mItem.hasBeenEditited = true;
                } catch (Exception e){
                    Log.d("ERROR", e.getMessage());
                }
                cName.setText(mItem.courseName);
                cID.setText(mItem.courseID);
                cProf.setText(mItem.courseProf);
                cDetails.setText(mItem.courseDetails);

            }
        });
        // Show the dummy content as text in a TextView.
/*        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.courseID);
            cName.setText(mItem.courseName);
            cID.setText(mItem.courseID);
            cProf.setText(mItem.courseProf);
            cDetails.setText(mItem.courseDetails);
        }*/

        return rootView;
    }
}
