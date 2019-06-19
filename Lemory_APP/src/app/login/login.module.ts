import { NgModule, NO_ERRORS_SCHEMA } from "@angular/core";
import { NativeScriptCommonModule } from "nativescript-angular/common";

import { LoginRoutingModule } from "./login-routing.module";
import { LoginComponent } from "./login.component";
import {RegisterComponent} from "~/app/register/register.component";

@NgModule({
    imports: [
        NativeScriptCommonModule,
        LoginRoutingModule
    ],
    declarations: [
       LoginComponent,
       RegisterComponent
    ],
    schemas: [
        NO_ERRORS_SCHEMA
    ]
})
export class LoginModule { }
