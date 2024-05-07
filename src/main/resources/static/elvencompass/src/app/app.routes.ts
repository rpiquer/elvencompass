import { Routes } from '@angular/router';
import { MapComponent } from './components/map/map.component';
import { PageNotFoundComponent } from './components/page-not-found/page-not-found.component';
import { LandingPageComponent } from './components/landing-page/landing-page.component';



export const routes: Routes = [
    {path: '', component: LandingPageComponent},
    {path: 'map', redirectTo: ''},
    {path: 'map/:location', component: MapComponent},
    {path: 'not-found', component: PageNotFoundComponent},
    {path: '**', redirectTo: 'not-found'}
];
