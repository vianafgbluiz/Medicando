package com.mediquei.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.mediquei.datamodel.LinkServerDataModel;
import com.mediquei.datamodel.MedicamentosDataModel;
import com.mediquei.datamodel.ResultadoBuscaDataModel;
import com.mediquei.datamodel.VerificacoesWebService;
import com.mediquei.model.ResultadoBusca;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.view.View.VISIBLE;

public class UtilResultadoBusca {

}
