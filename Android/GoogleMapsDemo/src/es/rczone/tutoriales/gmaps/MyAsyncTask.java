package es.rczone.tutoriales.gmaps;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;

public class MyAsyncTask extends AsyncTask<Void, Void, String> {
	
	private Activity context;
	private ProgressDialog progressDialog;
    private String url;
    private IResponse listener;
	
	public MyAsyncTask(Activity context,String urlPass){
		this.context = context;
		url = urlPass;
		listener = (IResponse)context;
	}

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage(context.getString(R.string.loading_message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }
    @Override
    protected String doInBackground(Void... params) {
        JSONParser jParser = new JSONParser();
        String json = jParser.getJSONFromUrl(url);
        return json;
    }
    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);   
        progressDialog.hide();        
        if(result!=null){
        	listener.drawPath(result);
        }
    }
}
