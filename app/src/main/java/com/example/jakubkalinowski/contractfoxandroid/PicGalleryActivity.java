package com.example.jakubkalinowski.contractfoxandroid;

import android.app.ProgressDialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PicGalleryActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageView mImageView;

    private String contractorID;

//    private DatabaseReference mFirebasePicturesReference;

    ProgressDialog mProgressDialog;

    private StorageReference mStorageReference;
    private StorageReference mStoragePath;
    public ArrayList<String> downloaded_images = new ArrayList<>();

    private ListView listView;

    private ImageView picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic_gallery);

//        listView = (ListView) findViewById(R.id.listView);
        picture = (ImageView) findViewById(R.id.pictureView);

        // Instantiating an adapter to store each image
//        ImageListAdapter adapter = new ImageListAdapter(getParent(),)

//        listView.setAdapter(new ImageListAdapter(PicGalleryActivity.this, downloaded_images));

        contractorID = getIntent().getExtras().getString("id");
        Log.i("contID-:", contractorID);
//
//        mImageView = (ImageView) findViewById(R.id.imageView);
//
//        mGridView = (GridView)findViewById(R.id.gridview);
//        mGridView.setAdapter(new ImageAdapter(this));
//
//        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent i = new Intent(PicGalleryActivity.this, ZoomIn.class);
//                i.putExtra("position", position);
//                startActivity(i);
//            }
//        });
//
        mStorageReference = FirebaseStorage.getInstance().getReference();

//        mProgressDialog = ProgressDialog.show(getApplicationContext(), "Uploading ...", "Please wait...", true);
//        mProgressDialog.show();

//                mProgressDialog.setMessage("Loading Gallery...");
//                mProgressDialog.show();


        mStoragePath = FirebaseStorage.getInstance().getReference("Before&AfterPictures").child(contractorID);

        mStoragePath.child("Before&AfterPictures/image1.png").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Got the download URL for Before&AfterPictures/image1.png

                Glide.with(PicGalleryActivity.this)
                        .using(new FirebaseImageLoader())
                        .load(mStoragePath)
                        .into(picture);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //Handle any errors
            }
        });
    }
}
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        StorageReference filePath = mStorageReference.child("Before&AfterPictureGallery").child(contractorID);
//
//        Uri uri = data.getData();
//
////        mProgressDialog.dismiss();
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
//                        downloaded_images.add(downloadUri.toString());
//                    }
//                    else {
//
//                        AlertDialog.Builder builder1 = new AlertDialog.Builder(PicGalleryActivity.this);
//                        builder1.setMessage("Galley is empty.");
//                        builder1.setCancelable(false);
//
//                        AlertDialog alert = builder1.create();
//                        alert.show();
//                    }
////                    Picasso.with(PicGalleryActivity.this)
////                        .load(downloadUri)
//////                        .placeholder(R.drawable.ic_placeholder)   // optional
//////                        .error(R.drawable.ic_error_fallback)      // optional
////                        .centerCrop()
//////                        .into((Target) mGridView);
////                        .into(mImageView);
//                }
//            }
//        });
//
//    }
//
//
//    private class ImageListAdapter extends ArrayAdapter {
//
//        private Context context;
//        private LayoutInflater inflater;
//
//        private List<String> imageUrls;
//
//        public ImageListAdapter(Context context, ArrayList<String> imageUrls) {
//            super(context, R.layout.activity_zoom_in, imageUrls);
//
//            this.context = context;
//            this.imageUrls = imageUrls;
//
//            inflater = LayoutInflater.from(context);
//        }
//
//        @Override
//        public View getView(int position, View convertView, ViewGroup parent) {
//            if (null == convertView) {
//                convertView = inflater.inflate(R.layout.activity_zoom_in, parent, false);
//            }
//
//            Picasso
//                    .with(context)
//                    .load(imageUrls.get(position))
//                    .fit()
//                    .into((ImageView) convertView);
//
//            return convertView;
//        }
//    }
//
//}
