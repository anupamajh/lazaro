//
//  HelpArticleView.swift
//  GuestJini
//
//  Created by Prasanna Kumar Pete on 21/01/20.
//  Copyright © 2020 Prasanna Kumar Pete. All rights reserved.
//

import SwiftUI

struct HelpArticleView: View {
    @ObservedObject var viewRouter: ViewRouter
    @ObservedObject var kbGetService:KBGetService
    
    @ObservedObject var getKbAuthorPicService:GetKBAuthorPicService
    @ObservedObject var kbGetRatingService:KBGetRatingService
    @ObservedObject var kbSaveRatingService:KBSaveRatingService
    @ObservedObject var kbSaveReviewService:KBSaveReviewService
    @ObservedObject var kbListReviewService:KBListReviewService
    
    @State var authorPic:Image = Image(systemName: "person")
    @State var reviewText:String = ""
    @State var isRatingSaved:Bool = false
    @State var isReviewSaved:Bool = true
    @State var showInternetDown:Bool = false
    
    init(viewRouter: ViewRouter){
        self.viewRouter = viewRouter
        self.kbGetService = KBGetService(viewRouter: viewRouter, id: viewRouter.primaryKey)
        self.getKbAuthorPicService = GetKBAuthorPicService(viewRouter: viewRouter, kbId: viewRouter.primaryKey)
        self.kbGetRatingService = KBGetRatingService(viewRouter: viewRouter, kbId: viewRouter.primaryKey)
        self.kbSaveRatingService = KBSaveRatingService(viewRouter: viewRouter)
        self.kbSaveReviewService = KBSaveReviewService(viewRouter: viewRouter)
        self.kbListReviewService = KBListReviewService(viewRouter: viewRouter,kbId: viewRouter.primaryKey)
        UITableView.appearance().separatorStyle = .none
    }
    
    var body: some View {
        GeometryReader { geometry in
            VStack{
                VStack{
                    ZStack{
                        ScrollView{
                            
                            HStack{
                                Button(action:{
                                    self.viewRouter.currentPage = ViewRoutes.FIND_HELP_PAGE
                                }){
                                    GuestJiniButtonSystemImagePlain(imageName: "arrow.left")
                                    Spacer()
                                }
                            }.padding()
                            VStack{
                                HStack{
                                    VStack{
                                        ZStack{
                                            self.getKbAuthorPicService.kbAuthorPic!
                                                .resizable()
                                                .clipShape(Circle())
                                                .overlay(Circle().stroke(Color.white, lineWidth: 1))
                                            if(self.getKbAuthorPicService.isLoading){
                                                ActivityIndicator(shouldAnimate: .constant(true), style: .medium)
                                            }
                                        }.onAppear(){
                                            self.getKbAuthorPicService.isLoading = true
                                            self.getKbAuthorPicService.getKbAuthorPic(kbId: self.viewRouter.primaryKey) { (response) in
                                                self.getKbAuthorPicService.kbAuthorPic = response;
                                                self.getKbAuthorPicService.isLoading = false
                                            }
                                        }.onDisappear(){
                                            self.getKbAuthorPicService.cancel()
                                        }
                                    }.frame(width: 45, height: 45, alignment: .center)
                                    VStack{
                                        HStack{
                                            Text("Author : \(self.kbGetService.kb.authorName!)")
                                                .font(Fonts.RobotSectionTitle)
                                                .foregroundColor(Color("greyishBrownFour"))
                                                .bold()
                                            Spacer()
                                        }
                                        HStack{
                                            Text(self.kbGetService.kb.creationTime!.convetToDateFromMySQL())
                                                .font(Fonts.RobotRegularSmallText)
                                                .foregroundColor(Color("brownishGrey"))
                                                .bold()
                                            Spacer()
                                        }
                                    }
                                    Spacer()
                                }.padding()
                                ZStack{
                                    VStack{
                                        HStack{
                                            Text(self.kbGetService.kb.topicTitle!)
                                                .font(Fonts.RobotTitle)
                                                .foregroundColor(Color("greyishBrownFour"))
                                                .bold()
                                            Spacer()
                                        }.padding(.bottom)
                                        HStack{
                                            VStack{
                                                Text(self.kbGetService.kb.topicNarration!)
                                                    .font(Fonts.RobotRegular)
                                                    .foregroundColor(Color("greyishBrownFour"))
                                                    .multilineTextAlignment(.leading)
                                                    .lineLimit(500)
                                                
                                            }
                                        }
                                    }.padding()
                                    
                                    if(!self.kbGetService.fetchComplete){
                                        ActivityIndicator(shouldAnimate: .constant(true), style: .large)
                                    }
                                    
                                }
                                
                                VStack{
                                    VStack{
                                        ZStack{
                                            VStack{
                                                HStack{
                                                    Text("Was this Helpful?")
                                                        .foregroundColor(Color("warmGrey"))
                                                        .font(Fonts.RobotSectionTitle)
                                                    Spacer()
                                                    VStack{
                                                        Button(action: {
                                                            self.isRatingSaved = false
                                                            let kbRating = KBRating();
                                                            kbRating.isLiked = 1
                                                            kbRating.kbId = self.viewRouter.primaryKey
                                                            self.kbSaveRatingService.saveRating(kbRating: kbRating) { (respose) in
                                                                self.kbGetRatingService.kbRating.isLiked = 1
                                                                self.isRatingSaved = true
                                                            }
                                                        }){
                                                            if(self.kbGetRatingService.fetchComplete || self.isRatingSaved){
                                                                if(self.kbGetRatingService.kbRating.isLiked == nil){
                                                                    GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsup")
                                                                }else if(self.kbGetRatingService.kbRating.isLiked == 1){
                                                                    GuestJiniRoundButtonSystemImage(systemImage: "hand.thumbsup")
                                                                }else{
                                                                    GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsup")
                                                                }
                                                            }else{
                                                                GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsup")
                                                            }
                                                            
                                                        }
                                                        Text("90%").font(Fonts.RobotRegularSmallText)
                                                    }
                                                    VStack{
                                                        Button(action: {
                                                            self.isRatingSaved = false
                                                            let kbRating = KBRating();
                                                            kbRating.isLiked = 0
                                                            kbRating.kbId = self.viewRouter.primaryKey
                                                            self.kbSaveRatingService.saveRating(kbRating: kbRating) { (respose) in
                                                                self.kbGetRatingService.kbRating.isLiked = 0
                                                                self.isRatingSaved = true
                                                            }
                                                        }){
                                                            if(self.kbGetRatingService.fetchComplete  || self.isRatingSaved){
                                                                if(self.kbGetRatingService.kbRating.isLiked == nil){
                                                                    GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsdown")
                                                                }else if(self.kbGetRatingService.kbRating.isLiked == 0){
                                                                    GuestJiniRoundButtonSystemImage(systemImage: "hand.thumbsdown")
                                                                }else{
                                                                    GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsdown")
                                                                }
                                                            }else{
                                                                GuestJiniWhiteRoundButtonSystemImage(systemImage: "hand.thumbsdown")
                                                            }
                                                        }
                                                        Text("10%").font(Fonts.RobotRegularSmallText)
                                                    }
                                                    
                                                }
                                                
                                            }.padding()
                                                .background(Color("paleGrey"))
                                            
                                        }
                                        
                                    }.padding(.top)
                                        .padding(.trailing)
                                        .padding(.bottom)
                                        .padding(.leading, 40)
                                        .cornerRadius(5)
                                }
                                
                                ZStack{
                                    VStack{
                                        VStack{
                                            ZStack{
                                                VStack{
                                                    HStack{
                                                        Text("Write a Review")
                                                            .foregroundColor(Color("warmGrey"))
                                                            .font(Fonts.RobotSectionTitle)
                                                        Spacer()
                                                    }
                                                    VStack{
                                                        MultilineTextView(text: self.$reviewText)
                                                            .frame(height:100, alignment: .top)
                                                            .padding(.all,10)
                                                            .font(Fonts.RobotRegular)
                                                            .background(Color.white)
                                                            .overlay(
                                                                RoundedRectangle(cornerRadius:5)
                                                                    .stroke(Color("veryLightPink"), lineWidth: 1)
                                                        )
                                                    }
                                                    HStack{
                                                        Button(action: {
                                                            if(self.reviewText.trimmingCharacters(in: .whitespacesAndNewlines) != ""){
                                                                let kbReview:KBReview = KBReview()
                                                                kbReview.kbId = self.viewRouter.primaryKey
                                                                kbReview.reviewComment = self.reviewText
                                                                self.isReviewSaved = false
                                                                self.kbSaveReviewService.saveReview(kbReview: kbReview) { (response) in
                                                                    self.isReviewSaved = true
                                                                    self.reviewText = ""
                                                                    self.kbListReviewService.fetchComplete = false
                                                                    self.kbListReviewService.getKBList(kbId: self.viewRouter.primaryKey) { (response) in
                                                                        self.kbListReviewService.fetchComplete = true
                                                                        self.kbListReviewService.kbReviewList = response.kbReviewList!
                                                                    }
                                                                }
                                                                
                                                            }
                                                            
                                                        }){
                                                            GuestJiniRectangularButtonText(buttonText: "SUBMIT")
                                                        }
                                                        Spacer()
                                                    }
                                                }.padding()
                                                    .background(Color("paleGrey"))
                                                
                                            }.resignKeyboardOnTapGesture()
                                            
                                        }.padding(.top)
                                            .padding(.trailing)
                                            .padding(.bottom)
                                            .padding(.leading, 40)
                                            .cornerRadius(5)
                                    }
                                    if(!self.isReviewSaved){
                                        ActivityIndicator(shouldAnimate: .constant(true), style: .large)
                                    }
                                }.resignKeyboardOnTapGesture()
                                
                                ZStack{
                                    VStack{
                                        ScrollView { ForEach(self.kbListReviewService.kbReviewList){ kbReview in
                                            VStack{
                                                KBReviewRow(kbReview: kbReview)
                                                Divider()
                                                    .padding(.bottom, 25)
                                                    .padding(.leading, 40)
                                            }
                                            }
                                        }
                                    }
                                    if(!self.kbListReviewService.fetchComplete){
                                        ActivityIndicator(shouldAnimate: .constant(true), style: .large)
                                    }
                                }
                            }
                        }.resignKeyboardOnTapGesture()
                        
                        GeometryReader { _ in
                            EmptyView()
                        }
                        .background(Color.black.opacity(0.8))
                        .opacity(self.showInternetDown ? 1.0 : 0.0)
                        if(self.showInternetDown){
                            GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!"))
                        }else{
                            GuestJiniAlerBox(showAlert: self.$showInternetDown, alertTitle: .constant("Oops!"), alertBody: .constant("Looks like internet connectivity is weak or not available!")).hidden()
                        }
                    }.onAppear(){
                        if(!Connectivity.isConnectedToInternet()){
                            self.kbGetService.fetchComplete = true
                            Connectivity.cancelAllRequests()
                            self.showInternetDown = true
                        }
                    }
                }.frame(width: geometry.size.width, height: geometry.size.height-85, alignment: .top)
                    .padding()
                    .resignKeyboardOnTapGesture()
                Divider()
                GuestJiniBottomBar(viewRouter: self.viewRouter)
                    .background(Color.white)
            }.frame(width: geometry.size.width, height: geometry.size.height, alignment: .top)
                .edgesIgnoringSafeArea(.vertical)
        }
    }
}

struct HelpArticleView_Previews: PreviewProvider {
    static var previews: some View {
        HelpArticleView(viewRouter: ViewRouter())
    }
}
