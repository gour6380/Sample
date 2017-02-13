package com.sulitous.mtc;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

public class WatingListFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnAddChildListener mAddListener;
    String[] mobileArray = {"Child 1","Child 2","Child 3","Child 4",
            "Child 5","Child 6","Child 7","Child 8"};

    String[] mtcCenters = {"MTC 1","MTC 2","MTC 3","MTC 4"};
    TextView mBedStatus;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.child_wating_list,container,false);
        ArrayAdapter adapter = new ArrayAdapter<>(getContext(),
                R.layout.child_list_item, mobileArray);

        ListView childListView = (ListView) rootView.findViewById(R.id.child_list);
        childListView.setAdapter(adapter);
        mBedStatus = (TextView) rootView.findViewById(R.id.bed_status);

        childListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.main, new ChildDetailsFragment(), "DETAILS");
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

        Spinner mMtcCenter = (Spinner) rootView.findViewById(R.id.mtc_center);
        ArrayAdapter mtcAdapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, mtcCenters);

        mtcAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mMtcCenter.setAdapter(mtcAdapter);
        mMtcCenter.setOnItemSelectedListener(this);

        setHasOptionsMenu(true);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.wating_list,menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_add:
                mAddListener.onAddChild();
                return false;
            default:
                break;
        }

        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (i){
            case 0:
                mBedStatus.setText("2 Bed Empty");
                return;
            case 1:
                mBedStatus.setText("3 Bed Empty");
                return;
            case 2:
                mBedStatus.setText("No Bed Empty");
                return;
            case 3:
                mBedStatus.setText("1 Bed Empty");
                return;
            default:
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public interface OnAddChildListener{
        void onAddChild();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        try {
            mAddListener = (OnAddChildListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAddListener = null;
    }
}
