package com.br.peladafc.gateway;

import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.util.UUID;

import static android.content.ContentValues.TAG;


/**
 * Created by alan on 04/11/2016.
 */

public final class PeladaFCWsGateway {

    private static final String logError = "on peladafc gateway: ";
    private static String baseURL = "http://10.0.2.2:55861/api/";

    private PeladaFCWsGateway(){}

    public static UUID CriarConta(CriarContaParams params) throws GatewayOperationException {

        try{
            JSONObject json = new JSONObject();
            json.put("NomeCompletoPeladeiro", params.nomeCompleto);
            json.put("Foto", params.foto == null ? null : Base64.encodeToString(params.foto, Base64.DEFAULT));
            JSONObject dados = post("Conta/Criar", json.toString());
            return UUID.fromString(dados.getString("Id"));
        } catch (JSONException e) {
           throw new GatewayOperationException(e.getMessage());
        }
    }

    private static JSONObject post(String webPath, String jsonParameter) throws GatewayOperationException {

        HttpURLConnection connection = null;

        try{
            URL url = new URL(baseURL + webPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setReadTimeout(10000);
            connection.setConnectTimeout(15000);
            connection.setRequestProperty("Content-Type","application/json");
            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(jsonParameter);
            writer.flush();
            writer.close();
            os.close();
            connection.connect();
            return getResposta(connection);

        }catch (ProtocolException e){
            Log.e(TAG, "on post: " + e.getMessage());
            throw new GatewayOperationException(e.getMessage());
        }
        catch (IOException e){
            Log.e(TAG, e.getMessage());
            throw new GatewayOperationException(e.getMessage());
        }
        finally {
            if(connection != null)
                connection.disconnect();
        }

    }

    private static JSONObject getResposta(HttpURLConnection connection) throws GatewayOperationException {

        try{
            int responseCode = connection.getResponseCode();

            if(responseCode == HttpURLConnection.HTTP_OK){
                StringBuilder builder = new StringBuilder();
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;

                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }

                return  new JSONObject(builder.toString());
            }
            else {
                Log.e(TAG, "on getResposta HTTP: " + responseCode);
                throw new GatewayOperationException("Erro de conexão com servidor");
            }

        }
        catch (IOException e){
            Log.e(TAG, "on getResposta: " + e.getMessage());
            throw new GatewayOperationException(e.getMessage());
        }
        catch (JSONException e){
            Log.e(TAG, "on getResposta: " + e.getMessage());
            throw new GatewayOperationException(e.getMessage());
        }
    }
}
