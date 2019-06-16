import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { D2GcrmSharedModule } from 'app/shared';
import {
  LeadComponent,
  LeadDetailComponent,
  LeadUpdateComponent,
  LeadDeletePopupComponent,
  LeadDeleteDialogComponent,
  leadRoute,
  leadPopupRoute
} from './';

const ENTITY_STATES = [...leadRoute, ...leadPopupRoute];

@NgModule({
  imports: [D2GcrmSharedModule, RouterModule.forChild(ENTITY_STATES)],
  declarations: [LeadComponent, LeadDetailComponent, LeadUpdateComponent, LeadDeleteDialogComponent, LeadDeletePopupComponent],
  entryComponents: [LeadComponent, LeadUpdateComponent, LeadDeleteDialogComponent, LeadDeletePopupComponent],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class D2GcrmLeadModule {}
