//
//  Sdk.swift
//  iosApp
//
//  Created by Dostonbek Kamalov on 12/19/20.
//  Copyright © 2020 orgName. All rights reserved.
//

import Foundation
import githubSdk

class SDK {
    
    static let shared = SDK()
    
    #if DEBUG
        private let isDebug = true
    #else
        private let isDebug = false
    #endif
    
    private let sdk: GithubSDK
    
    init() {
        sdk = GithubSDK.Companion().create(isDebug: isDebug)
    }
    
    var oAuthParams: OAuthParams {
        get { sdk.oAuthParams }
    }
    
    var loginInteractor: LoginInteractor {
        get {  sdk.getLoginInteractor() }
    }
}
