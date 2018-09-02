package gituser.com.gituser;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import gituser.com.gituser.Adapter.ItemAdapter;
import gituser.com.gituser.Model.BaseResponse;
import gituser.com.gituser.Model.Items;
import gituser.com.gituser.Utilities.API;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Unbinder bind;
    private Activity activity;
    private BaseResponse searchResponse=new BaseResponse();
    private List<Items> itemList = new ArrayList<>();
    private ItemAdapter mAdapter;
    private int previousItem,visibleItemTotal,itemsTotal;
    private int threshold=30;
    private int pageNumber=0;
    boolean isConnected;
    ConnectivityManager cm;
    NetworkInfo activeNetwork;
    LinearLayoutManager layoutManager;
    Toast toast;
    int duration = Toast.LENGTH_SHORT;

    @BindView(R.id.item_recycler_view) protected RecyclerView  mRecyclerView;
    @BindView(R.id.search_text) protected SearchView mSearchText;
    @BindView(R.id.toolbar) protected Toolbar mToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind= ButterKnife.bind(this);
        activity=this;
        setSupportActionBar(mToolbar);

        //check if device have conncection
        if(!checkConnection()){
            notConnected();
        }

        //when user hit search button on keyboard
        mSearchText.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    if(checkConnection()){  //check connection again if in the middle of app the device has connection
                        pageNumber=0; //reset pagenumber to zero
                        search(mSearchText.getQuery()+"",pageNumber); //go to search method
                    }else{
                        notConnected(); //notify if the device not connected to internet
                    }
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });

        //recylclerview scroll listener
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy>0 ){ //if recyclerview scroll down
                    visibleItemTotal=layoutManager.getChildCount(); //get visible item on device
                    previousItem=layoutManager.findFirstVisibleItemPosition(); //get previous item that has been show

                        if(itemsTotal==(previousItem+visibleItemTotal)){ //if previous item and visible item equals to item total in list
                            pageNumber=pageNumber+1; //increment page
                            itemsTotal=itemsTotal+threshold; //increment item total with threshold
                            pagination(mSearchText.getQuery()+"",pageNumber); // go to pagination
                        }

                }
            }
        });


    }
    public Boolean checkConnection(){
        cm = (ConnectivityManager)this.getSystemService(Context.CONNECTIVITY_SERVICE);
        activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;

    }
    public void notConnected(){
        toast = Toast.makeText(this, " you're not connected", duration);
        toast.show();
    }

    public void search(final String query_search,int page) {
            pageNumber++;
            page++;

        Call<BaseResponse> call = API.Factory.getInstance().getResponse(query_search,page);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                    if(response.code()==200){
                        searchResponse=response.body();
                        setResult();
                    }else{
                        pageNumber=pageNumber-1;
                        itemList.clear();
                        mAdapter = new ItemAdapter(itemList);
                        mAdapter.notifyDataSetChanged();
                        mRecyclerView.setAdapter(mAdapter);
                        reachLimit();
                    }

            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }

    public void reachLimit(){
        toast = Toast.makeText(this,getResources().getString(R.string.error_API_limit), duration);
        toast.show();
    }

    public void pagination(final String query_search,int page) {

        Call<BaseResponse> call = API.Factory.getInstance().getResponse(query_search,page);
        call.enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if(response.code()==200){
                    searchResponse = response.body();
                    if (!searchResponse.getItems_response().isEmpty()){
                        for (int i = 0; i < searchResponse.getItems_response().size(); i++) {
                            Items data = searchResponse.getItems_response().get(i);
                            itemList.add(data);
                        }
                        mAdapter.notifyDataSetChanged();
                    }
                }else{
                    pageNumber=pageNumber-1;
                    itemsTotal=itemsTotal-threshold;
                    reachLimit();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                Log.d("Error", t.getMessage());
            }
        });
    }


    public void setResult(){

        if(searchResponse.getItems_response().isEmpty()){
            itemList.clear();
            mAdapter = new ItemAdapter(itemList);
            toast = Toast.makeText(this, getResources().getString(R.string.error_search_user), duration);
            toast.show();
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
            itemsTotal=-1;
        }else{
            itemList= searchResponse.getItems_response();
            mAdapter = new ItemAdapter(itemList);
            layoutManager=new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(layoutManager);
            mAdapter.notifyDataSetChanged();
            mRecyclerView.setAdapter(mAdapter);
            itemsTotal=layoutManager.getItemCount();
        }


    }

}
