//
//  CarouselImage.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 26/12/19.
//  Copyright © 2019 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI


extension AnyTransition {
    static var rightToLeft: AnyTransition {
        let insertion = AnyTransition.move(edge: .trailing)
        let removal = AnyTransition.move(edge: .leading)
        return .asymmetric(insertion: insertion, removal: removal)
    }
}

struct CarouselImage: View {
    var backgroundImages:[String] = ["image1","image2"]
    @State private var backgroundImageIndex = 0
    @State private var backgroundImageNameEven = "image1"
    @State private var backgroundImageNameOdd = "image2"
    @State private var showImage = true
    
    var timer: Timer {
        Timer.scheduledTimer(withTimeInterval: 3, repeats: true){_ in
            if(self.backgroundImageIndex < self.backgroundImages.count-1){
                self.backgroundImageIndex += 1
            } else
            {
                self.backgroundImageIndex = 0
            }
            if(self.backgroundImageIndex % 2 == 0){
                self.backgroundImageNameEven = self.backgroundImages[self.backgroundImageIndex]
            } else {
                self.backgroundImageNameOdd = self.backgroundImages[self.backgroundImageIndex]
            }
            withAnimation{
                self.showImage.toggle()
            }
        }
    }
    
    var OddImage: some View {
        GuestJiniImageCard(image: backgroundImageNameOdd)
    }
    
    var EvenImage: some View {
        GuestJiniImageCard(image: backgroundImageNameEven)
    }
    
    var body: some View {
        ZStack{
            if(!self.showImage){
                OddImage
            }
            if(self.showImage){
                EvenImage
            }
        }.onAppear(perform: {
            let _ = self.timer
        }).edgesIgnoringSafeArea(.top)
    }
}
    
    struct CarouselImage_Previews: PreviewProvider {
        static var previews: some View {
            CarouselImage()
        }
}
