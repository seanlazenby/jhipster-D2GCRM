import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { D2GcrmSharedModule } from 'app/shared';
import {
  RegionComponent,
  RegionDetailComponent,
  RegionUpdateComponent,
  RegionDeletePopupComponent,
  RegionDeleteDialogComponent,
  regionRoute,
  regionPopupRoute
} from './';

const ENTITY_STATES = [...regionRoute, ...regionPopupRoute];

@NgModule({
  imports: [D2GcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [RegionComponent, RegionDetailComponent, RegionUpdateComponent, RegionDeleteDialogComponent, RegionDeletePopupComponent],
  entryComponents: [RegionComponent, RegionUpdateComponent, RegionDeleteDialogComponent, RegionDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmRegionModule {}
