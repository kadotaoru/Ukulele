package to.msn.wings.ukulele

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Display
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.WindowManager
import kotlinx.android.synthetic.main.activity_main.*
import android.media.MediaPlayer as MediaPlayer

class MainActivity : AppCompatActivity(), SurfaceHolder.Callback {
    private var frets: Array<Float> = arrayOf(0f, 0f, 0f, 0f)
    private var strings: Array<Float> = arrayOf(0f, 0f, 0f, 0f)
    private var screenHeight: Int = 0
    private var diffY: Int = 0

    // 13音mj
    private var c: MediaPlayer = MediaPlayer()
    private var cs: MediaPlayer = MediaPlayer()
    private var d: MediaPlayer = MediaPlayer()
    private var ds: MediaPlayer = MediaPlayer()
    private var e: MediaPlayer = MediaPlayer()
    private var f: MediaPlayer = MediaPlayer()
    private var fs: MediaPlayer = MediaPlayer()
    private var g: MediaPlayer = MediaPlayer()
    private var gs: MediaPlayer = MediaPlayer()
    private var a: MediaPlayer = MediaPlayer()
    private var asy: MediaPlayer = MediaPlayer()
    private var b: MediaPlayer = MediaPlayer()
    private var c2: MediaPlayer = MediaPlayer()


    override fun surfaceChanged(holder: SurfaceHolder?, format: Int, width: Int, height: Int) {
//        TODO("Not yet implemented")
        diffY = screenHeight - height
        val canvas = surfaceView.holder.lockCanvas()
        canvas.drawColor(Color.rgb(115, 66, 41)) //茶色
        val widthFret: Float = (height / 4).toFloat()
        val paint = Paint()
        paint.color = Color.rgb(42, 42, 42) //鉄黒
        paint.strokeWidth = 30f
        for (i in 1..4) {
            canvas.drawLine(0f, widthFret * i, width.toFloat(), widthFret * i, paint)
            frets[i - 1] = widthFret * 1
        }

        paint.color = Color.BLACK
        canvas.drawRect(0f, 0f, width.toFloat(), widthFret * 1, paint)

        val margin = 200f
        val stringInterval: Float = ((width - (margin * 2)) / 3).toFloat()
        paint.color = Color.LTGRAY
        paint.strokeWidth = 20f
        for (i in 0..3) {
            canvas.drawLine(
                margin + stringInterval * i,
                0f,
                margin + stringInterval * i,
                height.toFloat(),
                paint
            )
            strings[i] = margin + stringInterval * i
        }

        surfaceView.holder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
//        TODO("Not yet implemented")
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
//        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val holder = surfaceView.holder
        holder.addCallback(this)

        val windowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val display: Display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        screenHeight = size.y


        c = MediaPlayer.create(this, R.raw.c)
        cs = MediaPlayer.create(this, R.raw.cs)
        d = MediaPlayer.create(this, R.raw.d)
        ds = MediaPlayer.create(this, R.raw.ds)
        e = MediaPlayer.create(this, R.raw.e)
        f = MediaPlayer.create(this, R.raw.f)
        fs = MediaPlayer.create(this, R.raw.fs)
        g = MediaPlayer.create(this, R.raw.g)
        gs = MediaPlayer.create(this, R.raw.gs)
        a = MediaPlayer.create(this, R.raw.a)
        asy = MediaPlayer.create(this, R.raw.asy)
        b = MediaPlayer.create(this, R.raw.b)
        c2 = MediaPlayer.create(this, R.raw.c2)

    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        var stringNo: Int = 0
        var fretNo: Int = 0
        if (event?.action == MotionEvent.ACTION_DOWN) {
            when (event.x) {
                in (strings[0] - 200)..(strings[0] + 200)
                -> stringNo = 4
                in (strings[1] - 200)..(strings[1] + 200)
                -> stringNo = 3
                in (strings[2] - 200)..(strings[2] + 200)
                -> stringNo = 2
                in (strings[3] - 200)..(strings[3] + 200)
                -> stringNo = 1
            }
            when (event.y - diffY) {
                in 15f..(frets[0] - 15f) -> fretNo = 0
                in (frets[0] + 15f)..(frets[1] - 15f) -> fretNo = 1
                in (frets[1] + 15f)..(frets[2] - 15f) -> fretNo = 2
                in (frets[2] + 15f)..(frets[3] - 15f) -> fretNo = 3
            }
            playTone(stringNo, fretNo)
        }

        return true
    }

    private fun playTone(stringNo: Int, fretNo: Int) {
        when (stringNo) {
            4 -> {
                when (fretNo) {
                    0 -> {
                        g.seekTo(0)
                        g.start()
                    }
                    1 -> {
                        gs.seekTo(0)
                        gs.start()
                    }
                    2 -> {
                        a.seekTo(0)
                        a.start()
                    }
                    3 -> {
                        asy.seekTo(0)
                        asy.start()
                    }
                }
            }
            3 -> {
                when (fretNo) {
                    0 -> {
                        c.seekTo(0)
                        c.start()
                    }
                    1 -> {
                        cs.seekTo(0)
                        cs.start()
                    }
                    2 -> {
                        d.seekTo(0)
                        d.start()
                    }
                    3 -> {
                        ds.seekTo(0)
                        ds.start()
                    }
                }
            }
            2 -> {
                when (fretNo) {
                    0 -> {
                        e.seekTo(0)
                        e.start()
                    }
                    1 -> {
                        f.seekTo(0)
                        f.start()
                    }
                    2 -> {
                        fs.seekTo(0)
                        fs.start()
                    }
                    3 -> {
                        g.seekTo(0)
                        g.start()
                    }
                }
            }
            1 -> {
                when (fretNo) {
                    0 -> {
                        a.seekTo(0)
                        a.start()
                    }
                    1 -> {
                        asy.seekTo(0)
                        asy.start()
                    }
                    2 -> {
                        b.seekTo(0)
                        b.start()
                    }
                    3 -> {
                        c2.seekTo(0)
                        c2.start()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        c.release()
        cs.release()
        d.release()
        ds.release()
        e.release()
        f.release()
        fs.release()
        g.release()
        gs.release()
        a.release()
        asy.release()
        b.release()
        c2.release()
    }
}
