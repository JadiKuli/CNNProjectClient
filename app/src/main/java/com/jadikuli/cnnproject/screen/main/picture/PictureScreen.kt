import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.jadikuli.cnnproject.screen.main.picture.PictureViewModel
import com.jadikuli.cnnproject.screen.main.picture.UploadUiState
import java.io.File

@Composable
fun PictureScreen(
    viewModel: PictureViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        CameraPermissionScreen {
            PictureScreenContent(
                onImageCaptured = { uri ->
                    viewModel.uploadImage(context, uri)
                },
                onGalleryPick = { uri ->
                    viewModel.uploadImage(context, uri)
                }
            )
        }

        when (uiState) {
            is UploadUiState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is UploadUiState.Success -> {
                Text(
                    "Upload Success!",
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = Color.Green
                )
            }
            is UploadUiState.Error -> {
                Text(
                    "Error: ${(uiState as UploadUiState.Error).message}",
                    modifier = Modifier.align(Alignment.TopCenter),
                    color = Color.Red
                )
            }
            else -> {}
        }
    }
}

@Composable
fun PictureScreenContent(
    modifier: Modifier = Modifier,
    onImageCaptured: (Uri) -> Unit,
    onGalleryPick: (Uri) -> Unit
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val imageCapture = remember { ImageCapture.Builder().build() }
    val cameraProviderFuture = remember { ProcessCameraProvider.getInstance(context) }

    Box(modifier = modifier.fillMaxSize()) {
        AndroidView(
            modifier = Modifier.fillMaxSize(),
            factory = { ctx ->
                val previewView = PreviewView(ctx)

                val cameraProvider = cameraProviderFuture.get()
                val preview = Preview.Builder().build().also {
                    it.surfaceProvider = previewView.surfaceProvider
                }

                val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    lifecycleOwner,
                    cameraSelector,
                    preview,
                    imageCapture
                )

                previewView
            }
        )

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(200.dp)
                .border(3.dp, Color.Red, RoundedCornerShape(8.dp))
        )

        val launcher = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.GetContent()
        ) { uri: Uri? ->
            uri?.let { onGalleryPick(it) }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 116.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        val output = File(
                            context.externalCacheDir,
                            "${System.currentTimeMillis()}.jpg"
                        )
                        val uri = Uri.fromFile(output)

                        val outputOptions = ImageCapture.OutputFileOptions.Builder(output).build()
                        imageCapture.takePicture(
                            outputOptions,
                            ContextCompat.getMainExecutor(context),
                            object : ImageCapture.OnImageSavedCallback {
                                override fun onError(exc: ImageCaptureException) {
                                    Log.e("Camera", "Capture failed: ${exc.message}", exc)
                                }

                                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                                    onImageCaptured(uri)
                                }
                            }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Text("Take Picture")
                }

                Spacer(Modifier.width(12.dp))

                Button(
                    onClick = { launcher.launch("image/*") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    border = BorderStroke(1.dp, Color.Gray)
                ) {
                    Text("Pick from Gallery")
                }
            }
        }
    }
}


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun CameraPermissionScreen(
    onGranted: @Composable () -> Unit
) {
    val cameraPermission = rememberPermissionState(android.Manifest.permission.CAMERA)

    LaunchedEffect(Unit) {
        cameraPermission.launchPermissionRequest()
    }

    when {
        cameraPermission.status.isGranted -> {
            onGranted()
        }
        else -> {
            Text("Camera permission required")
        }
    }
}
