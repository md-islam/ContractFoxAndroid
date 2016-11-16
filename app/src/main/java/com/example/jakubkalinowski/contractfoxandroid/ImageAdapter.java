//package com.example.jakubkalinowski.contractfoxandroid;
//
//import android.content.Context;
//import android.content.Intent;
//import android.net.Uri;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.BaseAdapter;
//import android.widget.ImageView;
//
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//import com.squareup.picasso.Picasso;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static com.example.jakubkalinowski.contractfoxandroid.SearchViewListActivity.data;
//
///**
// * Created by jakubkalinowski on 11/15/16.
// */
//public class ImageAdapter extends BaseAdapter{
//
//    private Context mContext;
//
//    public ImageAdapter(Context c) {
//        mContext = c;
//    }
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return null;
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return 0;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        ImageView imageView;
//        //check to see if we have a view
//        if (convertView == null) {
//            // no view - so create a new one
//            // TODO: check line below if works
//            imageView = new ImageView(mContext);
//        } else {
//            //use the recycled view object
//            imageView = (ImageView) convertView;
//        }
//
//        Picasso.with(mContext)
//                .load(downloaded_images.get(position))
//                .noFade().resize(150,150)
//                .centerCrop()
//                .into(imageView);
//        return imageView;
//    }
//
//    private List<String> pics = new ArrayList<>();
//
//
//    private StorageReference mStorageReference;
//    private String contractorID;
//
//
//    StorageReference filePath = mStorageReference.child("Before&AfterPictureGallery").child(contractorID);
//
//
//
//        Uri uri = data.getData();
//
//        filePath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//
////                Uri downloadUri = taskSnapshot.getDownloadUrl();
//
//
//
//                Iterable<UploadTask.TaskSnapshot> taskSnapshotsList = (Iterable<UploadTask.TaskSnapshot>) taskSnapshot.getDownloadUrl();
//
//                for (UploadTask.TaskSnapshot snapshot : taskSnapshotsList) {
//
//                    Uri downloadUri = taskSnapshot.getDownloadUrl();
//
//                    if (snapshot.getDownloadUrl() != null) {
////                        downloaded_images.add(snapshot.toString());
//                        downloaded_images.add(downloadUri.toString());
//                    }
////                    Picasso.with(PicGalleryActivity.this)
////                        .load(downloadUri)
//////                        .placeholder(R.drawable.ic_placeholder)   // optional
//////                        .error(R.drawable.ic_error_fallback)      // optional
////                        .centerCrop()
////                        .into(mImageView);
//                }
//            }
//        });
//
//    }
//
//
//}
