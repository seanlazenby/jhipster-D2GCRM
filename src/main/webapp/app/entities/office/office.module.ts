import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { D2GcrmSharedModule } from 'app/shared';
import {
  OfficeComponent,
  OfficeDetailComponent,
  OfficeUpdateComponent,
  OfficeDeletePopupComponent,
  OfficeDeleteDialogComponent,
  officeRoute,
  officePopupRoute
} from './';

const ENTITY_STATES = [...officeRoute, ...officePopupRoute];

@NgModule({
  imports: [D2GcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [OfficeComponent, OfficeDetailComponent, OfficeUpdateComponent, OfficeDeleteDialogComponent, OfficeDeletePopupComponent],
  entryComponents: [OfficeComponent, OfficeUpdateComponent, OfficeDeleteDialogComponent, OfficeDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmOfficeModule {}
