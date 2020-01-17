//
//  FilePickerView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 17/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import Foundation
import SwiftUI
import MobileCoreServices
import Combine

struct FilePickerView: UIViewControllerRepresentable {
    
    @Binding var model: FilePickerViewModel
    
    
    typealias UIViewControllerType = UIDocumentPickerViewController
    
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    
    func makeUIViewController(context: Context) -> UIDocumentPickerViewController {
        let controller = UIDocumentPickerViewController(documentTypes: [String(kUTTypeText)], in: .open)
        controller.delegate = context.coordinator
        return controller
    }
    
    func updateUIViewController(_ uiViewController: UIDocumentPickerViewController, context: UIViewControllerRepresentableContext<FilePickerView>) {
        // Update the controller
    }
    
    class Coordinator: NSObject, UIDocumentPickerDelegate {
        var parent: FilePickerView
        
        init(_ pickerController: FilePickerView) {
            self.parent = pickerController
        }
        
        func documentPicker(_ controller: UIDocumentPickerViewController, didPickDocumentsAt urls: [URL]) {
            guard urls[0].startAccessingSecurityScopedResource() else {
                // Handle the failure here.
                return
            }
            parent.model.sourceFile = urls[0]
            parent.model.isPresented = false
            let cert = NSData(contentsOfFile: urls[0].path)
            parent.model.sourceFileData = cert as Data?
            parent.model.sourceFileName = urls[0].lastPathComponent
            let filePickerUIView = FilePickerUIView(fileURL: urls[0])
            parent.model.pickedFileSubject.send(filePickerUIView)
            print("Document Picked")
        }
        
        func documentPicker(didPickDocumentsAt: [URL]) {
            
        }
        
        func documentPickerWasCancelled() {
            parent.model.isPresented = false
        }
        
        deinit {
            print("Coordinator going away")
        }
    }
}

struct FilePickerViewModel {
    var isPresented: Bool = false
    var sourceFile:URL? = nil
    var sourceFileData:Data? = nil
    var sourceFileName:String? = nil
    let pickedFileSubject: PassthroughSubject<FilePickerUIView, Never>! = PassthroughSubject<FilePickerUIView, Never>()
}

struct FilePickerView_Previews: PreviewProvider {
    static var previews: some View {
        FilePickerView(model: .constant(FilePickerViewModel()))
    }
}
