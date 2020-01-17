//
//  AudioRecorderView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI
import Combine
import AVFoundation
import AVKit
import MobileCoreServices

struct AudioRecorderView: View {
    @ObservedObject var audioRecorder: AudioRecorder
    @Binding var model: AudioPlayerViewModel
     var body: some View {
        VStack {
            if audioRecorder.recording == false {
                Button(action: {print(self.audioRecorder.startRecording())}) {
                    Image(systemName: "circle.fill")
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: 100, height: 100)
                        .clipped()
                        .foregroundColor(.red)
                        .padding(.bottom, 40)
                }
            } else {
                Button(action: {
                    self.audioRecorder.stopRecording()
                    self.model.isPresented = false
                }) {
                    Image(systemName: "stop.fill")
                        .resizable()
                        .aspectRatio(contentMode: .fill)
                        .frame(width: 100, height: 100)
                        .clipped()
                        .foregroundColor(.red)
                        .padding(.bottom, 40)
                }
            }
        }
    }
}

struct AudioPlayerViewModel {
    var isPresented: Bool = false
    var sourceAudio:URL? = nil
}

struct AudioRecorderView_Previews: PreviewProvider {
    static var previews: some View {
        AudioRecorderView(audioRecorder: AudioRecorder(), model: .constant(AudioPlayerViewModel()))
    }
}
