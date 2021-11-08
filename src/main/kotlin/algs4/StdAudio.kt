package algs4

import kotlinx.coroutines.*
import java.io.ByteArrayInputStream
import java.io.File
import java.io.IOException
import java.net.URL
import javax.sound.sampled.*
import kotlin.experimental.and
import kotlin.math.PI
import kotlin.math.sin
import kotlin.random.Random


/**
 * Standard Audio. This class provides a basic capability for creating, reading and saving audio
 *
 * The audio format uses a samplingrate of 44,100 Hz, 16-bit, monarual
 *
 * For additional documentation, see <a href="https://introcs.cs.princeton.edu/15inout">Section 1.5</a> of
 * Computer Science: An Interdisciplinary Approach by Robert Sedgewick and Kevin Wayne
 */
object StdAudio {

    /**
     * The Sample rate: 44,100 Hz for CD quality audio
     */
    const val SAMPLE_RATE = 44_100

    private const val BYTES_PER_SAMPLE   = 2
    private const val BITS_PER_SAMPLE    = 16
    private const val MAX_16_BIT         = 32768
    private const val SAMPLE_BUFFER_SIZE = 4096

    private const val MONO   = 1
    private const val STEREO = 2

    private const val LITTLE_ENDIAN = false
    private const val BIG_ENDIAN    = true
    private const val SIGNED        = true
    private const val UNSIGNED      = false

    private lateinit var line: SourceDataLine // to play the sound
    private lateinit var buffer: ByteArray // our internal buffer
    private var bufferSize = 0 // number of samples currently in internal buffer

    // open up an audio steam
    init {
        try {
            val format = AudioFormat(SAMPLE_RATE.toFloat(), BITS_PER_SAMPLE, MONO, SIGNED, LITTLE_ENDIAN)
            val info = DataLine.Info(SourceDataLine::class.java, format)

            line = AudioSystem.getLine(info) as SourceDataLine
            line.open(format, SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE)

            // the internal buffer is a fraction of the actual buffer size, this choice is arbitrary
            // it gets divided because we can't expect the buffered data to line up exactly with when
            // the sound card decides to push out its samples.
            buffer = ByteArray(SAMPLE_BUFFER_SIZE * BYTES_PER_SAMPLE)
        } catch (e: LineUnavailableException) {
            println(e.message)
        }

        // no sound gets made before this call
        line.start()
    }

    /**
     * get an AudioInputStream object from a file
     */
    private fun getAudioInputStreamFromFile(filename: String): AudioInputStream {
        try {
            // first try to read file from local file system
            val file = File(filename)
            if (file.exists()) return AudioSystem.getAudioInputStream(file)

            // resource relative to .class file
            val is1 = StdAudio.javaClass.getResourceAsStream(filename)
            if (is1 != null) return AudioSystem.getAudioInputStream(is1)

            // resource relative to classloader root
            val is2 = StdAudio.javaClass.classLoader.getResourceAsStream(filename)
            if (is2 != null) return AudioSystem.getAudioInputStream(is2)

            // from URL
            val url = URL(filename)
            if (url.file != null) return AudioSystem.getAudioInputStream(url)

            else throw IllegalArgumentException("could not read $filename")
        }
        catch (e: IOException) {
            throw IllegalArgumentException("could not read $filename")
        }
        catch (e: UnsupportedAudioFileException) {
            throw IllegalArgumentException("file of unsupported audio format $filename")
        }
    }

    /**
     * Closes standard audio
     */
    fun close() {
        line.drain()
        line.stop()
    }

    /**
     * Writes one sample (between -1.0 and +1.0) to standard audio.
     * If sample is outside of the range, it will be clipped.
     *
     * @param sample the sample to play
     * @throws IllegalArgumentException if the sample is [Double.NaN]
     */
    fun play(sample: Double) {
        if (sample.isNaN()) throw  IllegalArgumentException("sample is NaN")
        var sample_ = sample
        if (sample < -1.0) sample_ = -1.0
        if (sample >  1.0) sample_ =  1.0

        var s: Short = (MAX_16_BIT * sample_).toInt().toShort()
        if (sample_ == 1.0) s = Short.MAX_VALUE // special case since 32768

        buffer[bufferSize++] = s.toByte()
        buffer[bufferSize++] = (s.toInt() shr 8).toByte()

        if (bufferSize >= buffer.size) {
            line.write(buffer, 0, buffer.size)
            bufferSize = 0
        }
    }

    /**
     * Writes the array of samples (between -1.0 and +1.0) to standard audio.
     * If a sample is outside the range, it will be clipped
     *
     * @param samples the array of samples the play
     * @throws IllegalArgumentException if any sample is [Double.NaN]
     */
    fun play(samples: DoubleArray) {
        samples.forEach { play(it) }
    }

    /**
     * Reads audio samples from a file (in .wav or .au format) and returns
     * them as a double array with values between -1.0 and +1.0.
     * The audio file must be 16-bit with sampling rate of 44,100.
     * It can be mono or stereo.
     *
     * @param filename the name of the audio file
     * @return the array of samples
     */
    fun read(filename: String): DoubleArray {

        // make sure that AudioFormat is 16-bit, 44,100 Hz, little endian
        val ais = getAudioInputStreamFromFile(filename)
        val audioFormat = ais.format

        // require sampling rate = 44,100 Hz
        if (audioFormat.sampleRate != SAMPLE_RATE.toFloat()) {
            throw IllegalArgumentException("StdAudio.read() currently supports only a sample rate of $SAMPLE_RATE " +
                    "Hz audio format: $audioFormat")
        }

        // require 16-bit audio
        if (audioFormat.sampleSizeInBits != BITS_PER_SAMPLE) {
            throw  IllegalArgumentException("StdAudio.read() currently supports only $BITS_PER_SAMPLE-bit audio " +
                    "audio format: $audioFormat")
        }

        // require little endian
        if (audioFormat.isBigEndian) {
            throw IllegalArgumentException("StdAudio.read() currently supports only audio stored using little endian" +
                    "audio format: $audioFormat")
        }

        val bytes: ByteArray
        try {
            val bytesToRead = ais.available()
            bytes = ByteArray(bytesToRead)
            val bytesRead = ais.read(bytes)
            if (bytesToRead != bytesRead) {
                throw IllegalStateException("read only $bytesRead of $bytesToRead bytes")
            }
        } catch (e: IOException) {
            throw IllegalArgumentException("could not read $filename', $e")
        }

        val n = bytes.size

        // little endian, mono
        return when (audioFormat.channels) {
            MONO -> {
                DoubleArray(n/2) { i ->
                    ((bytes[2 * i + 1] and 0xFF.toByte()).toInt() shl 8 or (bytes[2 * i] and 0xFF.toByte()).toInt()).toShort() / MAX_16_BIT.toDouble() }
            }
            STEREO -> {
                DoubleArray(n/4) { i ->
                    val left =
                        ((bytes[4 * i + 1] and 0xFF.toByte()).toInt() shl 8 or (bytes[4 * i + 0] and 0xFF.toByte()).toInt()).toShort() / MAX_16_BIT.toDouble()
                    val right =
                        ((bytes[4 * i + 3] and 0xFF.toByte()).toInt() shl 8 or (bytes[4 * i + 2] and 0xFF.toByte()).toInt()).toShort() / MAX_16_BIT.toDouble()
                    (left + right) / 2.0
                }
            }
            else -> throw IllegalStateException("audio format is neither mono or stereo")
        }
    }

    /**
     * Saves the double array as an audio file (using .wav or .au format).
     *
     * @param filename the name of the audio file
     * @param samples the array of samples
     * @throws IllegalArgumentException if unable to save [filename]
     * @throws IllegalArgumentException if [filename] extention is not [.wav] or [.au]
     */
    fun save(filename: String, samples: DoubleArray){
        // assumes 16-bit samples with sample rate = 44,100 Hz
        // use 16-bit audio, mono, signed PCM, little Endian
        val format = AudioFormat(SAMPLE_RATE.toFloat(), 16, MONO, SIGNED, LITTLE_ENDIAN)
        val data = ByteArray(2 * samples.size)

        for (i in samples.indices) {
            var temp = (samples[i] * MAX_16_BIT).toInt().toShort()
            if (samples[i] == 1.0) temp = Short.MAX_VALUE
            data[2*i + 0] = temp.toByte()
            data[2*i + 1] = (temp.toInt() shr 8).toByte()
        }

        // now save the file
        try {
            val bais = ByteArrayInputStream(data)
            val ais = AudioInputStream(bais, format, samples.size.toLong())
            if (filename.endsWith(".wav") || filename.endsWith(".WAV")) {
                AudioSystem.write(ais, AudioFileFormat.Type.WAVE, File(filename))
            } else if (filename.endsWith(".au") || filename.endsWith(".AU")) {
                AudioSystem.write(ais, AudioFileFormat.Type.AU, File(filename))
            } else {
                throw IllegalArgumentException("file type for saving must be .wav or .au")
            }
        } catch (e: IOException) {
            throw IllegalArgumentException("unable to save file $filename")
        }
    }

    /**
     * Plays an audio file (in .wav, .mid, or .au format) in background thread
     *
     * @param filename the name of the audio file
     * @throws IllegalArgumentException if unable to play [filename]
     */
    suspend fun playInBackground(filename: String) {
        withContext(Dispatchers.Default) {
            val ais = getAudioInputStreamFromFile(filename)
            stream(ais)
        }
    }

    fun stream(ais: AudioInputStream) {
        var line: SourceDataLine? = null
        val BUFFER_SIZE = 4096

        try {
            val audioFormat = ais.format
            val info = DataLine.Info(SourceDataLine::class.java, audioFormat)
            line = AudioSystem.getLine(info) as SourceDataLine
            line.open(audioFormat)
            line.start()
            var samples = ByteArray(BUFFER_SIZE)
            var count = ais.read(samples, 0, BUFFER_SIZE)
            while (count != -1) {
                line.write(samples, 0, count)
                count = ais.read(samples, 0, BUFFER_SIZE)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        } finally {
            if (line != null) {
                line.drain()
                line.close()
            }
        }
    }

    /**
     * Loops an audio file in a background thread
     *
     * @param  filename the file name of the audio file
     */
    suspend fun loopInBackground(filename: String) {
        withContext(Dispatchers.Default) {
            val ais = getAudioInputStreamFromFile(filename)

            try {
                val clip = AudioSystem.getClip()
                clip.open(ais)
                clip.loop(Clip.LOOP_CONTINUOUSLY)
            } catch (e: LineUnavailableException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
}

fun main() {
    //val freq = 440.0
    //for (i in 0..StdAudio.SAMPLE_RATE) {
    //    StdAudio.play(0.5 * sin(2 * PI * freq * i / StdAudio.SAMPLE_RATE))
    //}
    StdAudio.save("kotlin/dataset/test2.wav",
        DoubleArray(4000){ Random.nextDouble(-1.0, 1.0)})
    StdAudio.close()
}