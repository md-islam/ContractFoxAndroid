package com.example.jakubkalinowski.contractfoxandroid;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class PicGalleryActivity extends AppCompatActivity {

    private GridView mGridView;
    private ImageView mImageView;

    private String contractorID;

    ProgressDialog mProgressDialog;

    private StorageReference mStorageReference;
    private StorageReference mImagesRef;
    private StorageReference mStoragePath;
    public ArrayList<String> downloaded_images = new ArrayList<>();

    private ListView listView;

    private ImageView picture;
    private ImageView picture2;
    private ImageView picture3;
    private ImageView picture4;



    private FirebaseStorage storage;
    private StorageReference storageRef;
    private StorageReference galleryImg;

    private ImageView mGalleryPic;
    private Bitmap mGalleryPicBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pic_gallery);
        storage = FirebaseStorage.getInstance();
        storageRef = storage.getReferenceFromUrl("gs://contract-fox.appspot.com");

//        listView = (ListView) findViewById(R.id.listView);
        picture = (ImageView) findViewById(R.id.pictureView);
        picture2 = (ImageView) findViewById(R.id.pictureView2);
        picture3 = (ImageView) findViewById(R.id.pictureView3);
        picture4 = (ImageView) findViewById(R.id.pictureView4);


        // Instantiating an adapter to store each image
//        ImageListAdapter adapter = new ImageListAdapter(getParent(),)

//        listView.setAdapter(new ImageListAdapter(PicGalleryActivity.this, downloaded_images));


        savedInstanceState = getIntent().getExtras();
        contractorID = savedInstanceState.getString("id");
//        contractorID = getIntent().getExtras().getString("id");
        Log.i("contID-:", contractorID);
        System.out.println("*********************************** contractor id is: "+contractorID);
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

//        mStorageReference = FirebaseStorage.getInstance().getReference();

//        mProgressDialog = ProgressDialog.show(getApplicationContext(), "Uploading ...", "Please wait...", true);
//        mProgressDialog.show();

//                mProgressDialog.setMessage("Loading Gallery...");
//                mProgressDialog.show();

////////
//        mStoragePath = FirebaseStorage.getInstance().getReference("Before&AfterPictures").child(contractorID);
////        mStoragePath = FirebaseStorage.getInstance().getReference("Before&AfterPictures/"+contractorID);
//
//        String dataUri = mStoragePath.getDownloadUrl().toString();
//
////////        Picasso.with(PicGalleryActivity.this).load(dataUri).into(picture);

//        galleryImg = storageRef.child("Before&AfterPictureGallery/"+contractorID);
        galleryImg = FirebaseStorage.getInstance().getReference("Before&AfterPictureGallery/"+contractorID+"/img");
        // Picasso or Glide
        Glide.with(PicGalleryActivity.this)
                .using(new FirebaseImageLoader())
                .load(galleryImg)
                .into(picture);

        Glide.with(PicGalleryActivity.this)
                .using(new FirebaseImageLoader())
                .load(galleryImg)
                .into(picture2);

        Glide.with(PicGalleryActivity.this)
                .using(new FirebaseImageLoader())
                .load(galleryImg)
                .into(picture3);

        Glide.with(PicGalleryActivity.this)
                .using(new FirebaseImageLoader())
                .load(galleryImg)
                .into(picture4);


//TOOK OUT FOR TESTING
//        mStoragePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            //Got the download URL for Before&AfterPictures/image1.png
//
//                Log.i("imgRef- ", mStoragePath.getPath());
//
//                Glide.with(PicGalleryActivity.this)
//                        .using(new FirebaseImageLoader())
//                        .load(mStoragePath)
//                        .into(picture);
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                //Handle any errors
//            }
//        });


//        });
//
//        byte[] profilePictureBytedata = getCircleGalleryPictureViewByteData();
//        mGalleryPicPath = FirebaseStorage.getInstance().getReference("Before&AfterGalleryPicture").child(user.getUid().toString());
//        mGalleryPicPath.putBytes(galleryPictureBytedata);
//
//        mGalleryPic = (ImageView) findViewById(R.id.pictureView);
//
//        // Profile Photo & Logo upload
//        mGalleryPic.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                verifyStoragePermissions(PicGalleryActivity.this);
//                showEditGalleryPictureDialog();
//            }
//        });
//
//        //This piece of code gets ByteArrayData from whatever the image circle IS//still needs
//        // refinement
//        public byte[] getCircleGalleryPictureViewByteData() {
//
//            mGalleryPic.setDrawingCacheEnabled(true);
//            mGalleryPic.buildDrawingCache();
//            mGalleryPicBitmap = mGalleryPic.getDrawingCache();
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//            mGalleryPicBitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//            byte[] data1 = baos.toByteArray();
//            return data1;
//        }
//
//        //This piece of code pops up the dialog window
//        //Found from https://github.com/afollestad/material-dialogs
//    public void showEditProfilePictureDialog() {
//        option = "profile";
//        MaterialDialog.Builder builder = new MaterialDialog.Builder(getContext())
//                .title("Set Profile Picture").positiveText("CAMERA").neutralText("DEVICE STORAGE")
//                .onNeutral(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
//                        mPickImageIntent = new Intent(Intent.ACTION_PICK, MediaStore
//                                .Images.Media.EXTERNAL_CONTENT_URI);
//                        mPickImageIntent.putExtra("option", option);
//                        startActivityForResult(mPickImageIntent, IMG_RESULT);
//                    }
//                });
//        MaterialDialog md = builder.build();
//        md.show();
//    }
//
//
//    }
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        try {
//            if (requestCode == IMG_RESULT && resultCode == Activity.RESULT_OK && null != data) {
//                Uri URI = data.getData();
//                String[] FILE = {MediaStore.Images.Media.DATA};
//
//                Cursor cursor = getActivity().getContentResolver().query(URI,
//                        FILE, null, null, null);
//
//                cursor.moveToFirst();
//                int columnIndex = cursor.getColumnIndex(FILE[0]);
//                String imageDecode = cursor.getString(columnIndex);
//
//                //compressing the image
//                Bitmap imageToCompress = BitmapFactory.decodeFile(imageDecode);
//                ByteArrayOutputStream out = new ByteArrayOutputStream();
//                imageToCompress.compress(Bitmap.CompressFormat.JPEG, 100, out);
//                cursor.close();
//
//
//                //TODO: Test this logic
//                //image is compressed and then set to imageView/
//                if (option.equals("photo")) {
//                    mProfilePic.setImageBitmap(BitmapFactory.decodeStream(
//                            new ByteArrayInputStream(out.toByteArray())));
//                } else {
//                    mLogoImg.setImageBitmap(BitmapFactory.decodeStream(
//                            new ByteArrayInputStream(out.toByteArray())));
//                }
//            }
//        } catch (Exception e) {
//            Toast.makeText(getActivity().
//                    getApplicationContext(), "Something went wrong, try again", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//    //this piece of code below is to make sure app has permissions to access device photos/contents
//    //This is new in Android 6.0 Marshmallow. It's called Runtime permissions.
//    //Specifying permissions in Android Manifest file is not enough.
//    public static void verifyStoragePermissions(Activity activity) {
//        //check if we have read or write permissions
//        int writePermission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.
//                WRITE_EXTERNAL_STORAGE);
//        int readPermission = ActivityCompat.checkSelfPermission(activity, android.Manifest.permission.
//                READ_EXTERNAL_STORAGE);
//
//        if (writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager
//                .PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
//        }
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
