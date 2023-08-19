package com.example.homeservices;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private final String JSON_URL = "https://run.mocky.io/v3/301384c5-7009-419f-9b93-95cab341472d";

    private StringRequest request;
    private RequestQueue requestQueue;
    private List<worker_model> model;
    private RecyclerView recyclerView;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_home, container, false);
        ImageButton homeButton = (ImageButton) myView.findViewById(R.id.homeButton);
        ImageButton plumberButton = (ImageButton) myView.findViewById(R.id.plumberButton);
        ImageButton electricianButton = (ImageButton) myView.findViewById(R.id.electricianButton);
        ImageButton painterButton = (ImageButton) myView.findViewById(R.id.painterButton);
        ImageButton backButton = (ImageButton) myView.findViewById(R.id.backButton);
        ImageButton profileButton = (ImageButton) myView.findViewById(R.id.profileButton);
        final LinearLayout homeLayout = myView.findViewById(R.id.homeLayout);
        final LinearLayout calenderLayout = myView.findViewById(R.id.calenderLayout);
        final LinearLayout aboutLayout = myView.findViewById(R.id.aboutLayout);
        final LinearLayout profileLayout = myView.findViewById(R.id.profileLayout);
        final LinearLayout bottomBar = myView.findViewById(R.id.bottomBar);




        model = new ArrayList<>();
        recyclerView = myView.findViewById(R.id.recyclerView);
        jsonrequest();


        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeButtonIntent = new Intent(getActivity(),CleanerActivity.class);
                startActivity(homeButtonIntent);
            }
        });

        plumberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent plumberButtonIntent = new Intent(getActivity(),PlumberActivity.class);
                startActivity(plumberButtonIntent);
            }
        });

        electricianButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent electricianButtonIntent = new Intent(getActivity(),ElectricianActivity.class);
                startActivity(electricianButtonIntent);
            }
        });


        painterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent painterButtonIntent = new Intent(getActivity(),PainterActivity.class);
                startActivity(painterButtonIntent);
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent backButton = new Intent(getActivity(),login.class);
                startActivity(backButton);
            }
        });

        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                FragmentTransaction fr = getFragmentManager().beginTransaction();
//                fr.replace(R.id.homeFragment,new ProfileFragment());
//                fr.commit();

               Intent ProfileButton = new Intent(getActivity(),ProfileActivity.class);
               startActivity(ProfileButton);



            }
        });



        // Inflate the layout for this fragment
          return myView;
    }


    private void jsonrequest() {

        request = new StringRequest(Request.Method.GET,JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("recommended");


                    for (int i=0; i<jsonArray.length();i++){


                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        worker_model workerModel = new worker_model();
                        workerModel.setName(jsonObject1.getString("name"));
                        workerModel.setField(jsonObject1.getString("field"));
                        workerModel.setRating(jsonObject1.getString("rating"));
                        workerModel.setPrice(jsonObject1.getString("price"));
                        workerModel.setImage(jsonObject1.getString("image"));
                        model.add(workerModel);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                setuprecyclerview(model);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag","onErrorResponse:"+error.getMessage());
            }

        });

        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);
    }

    private void setuprecyclerview(List<worker_model> model) {

        workerAdapter adapter = new workerAdapter(getActivity(),model);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }




}