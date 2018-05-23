package com.example.pengzhang.fridgepay.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment
{
   private static String TITLE = "TITLE";
   private static String BODY = "BODY";
   public static String DATA = "DATA";


   public static Fragment newInstance(String title, String body, String data) {
      AlertDialogFragment fragment = new AlertDialogFragment();
      Bundle bundle = new Bundle(  );
      bundle.putString( TITLE, title );
      bundle.putString( BODY, body );
      bundle.putString( DATA, data );
      fragment.setArguments( bundle );
      fragment.setCancelable(false);
      return fragment;
   }

   @Override
   public Dialog onCreateDialog(Bundle savedInstanceState) {
      String title = getArguments().getString( TITLE );
      String body = getArguments().getString( BODY );
      final String data = getArguments().getString( DATA );

      return new AlertDialog.Builder(getActivity())
         .setTitle(title)
         .setMessage(body)
         .setCancelable(false)
         .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               sendResult( Activity.RESULT_OK, data);
            }
         })
         .setNegativeButton(android.R.string.cancel, null)
         .create();
   }

   public void sendResult(int resultCode, String data) {
      Intent intent = new Intent( );
      intent.putExtra( DATA, data );
      getTargetFragment().onActivityResult(getTargetRequestCode(), resultCode, intent);
   }

}
