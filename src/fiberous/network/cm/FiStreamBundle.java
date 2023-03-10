/*
 * BSD 3-Clause License
 *
 * Copyright (c) 2023, Noah McLean
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * 3. Neither the name of the copyright holder nor the names of its
 *    contributors may be used to endorse or promote products derived from
 *    this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package fiberous.network.cm;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A simple object that contains a {@link DataInputStream}, a {@link DataOutputStream}, and the Socket the streams were derived from for convenience.
 * It is constructed from information provided by a {@link Socket}.
 * It has a couple convenience methods included.
 * The primary purpose of this class is to provide easy access to a Socket's IO streams.
 * While the Socket itself is included, typically, you would conduct open/close operations with the Socket indirectly by using the {@link FiConnectionManager}.
 * This way the FiConnectionManager will maintain an accurate record of current connections.
 * In in smaller scaled applications, this may not matter.
 * @author Noah
 *
 */
public class FiStreamBundle {
	private Socket socket;
	private DataInputStream iStream;
	private DataOutputStream oStream;
	
	/**
	 * Constructs the StreamBundle by deriving DataInputStreams and DataOutputStreams from the
	 * Socket that is passed.
	 * @param s
	 * @throws IOException
	 */
	public FiStreamBundle(Socket s) throws IOException {
		socket = s;
		iStream = new DataInputStream(s.getInputStream());
		oStream = new DataOutputStream(s.getOutputStream());
	}
	
	/**
	 * Convenience method to easily grab a string from the DataInputStream.
	 * @return
	 * @throws IOException
	 */
	public String readUTFData() throws IOException {
		return iStream.readUTF();
	}
	
	/**
	 * Convenience method to easily send a string with the DataOutputStream.
	 * @param data
	 * @throws IOException
	 */
	public void writeUTFData(String data) throws IOException {
		oStream.writeUTF(data);
	}
	
	/**
	 * Returns the Socket that is associated with this StreamBundle.  This should only
	 * be used for reference purposes.  Preferred Socket interaction is through the
	 * {@link FiConnectionManager} or directly with the Socket itself, if it was created
	 * externally, without the use of a ConnectionManager.
	 * @return
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Returns the DataInputStream associated with this StreamBundle.
	 * @return {@link DataInputStream}
	 */
	public DataInputStream getInputStream() {
		return iStream;
	}
	
	/**
	 * Returns the DataOutputStream associated with this StreamBundle.
	 * @return {@link DataOutputStream}
	 */
	public DataOutputStream getOutputStream() {
		return oStream;
	}
	
	/**
	 * Closes the DataInputStream and DataOutputStream associated with this StreamBundle.
	 * IT DOES NOT close the Socket.
	 * @throws IOException
	 */
	public void closeStreams() throws IOException {
		iStream.close();
		oStream.close();
	}
	
	/**
	 * Closes the DataInputStream, DataOutputStream, and the Socket associated with this StreamBundle.
	 * @throws IOException
	 */
	public void close() throws IOException {
		iStream.close();
		oStream.close();
		socket.close();
	}
}